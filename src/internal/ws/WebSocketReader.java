/*     */ package okhttp3.internal.ws;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.net.ProtocolException;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.internal.Util;
/*     */ import okio.Buffer;
/*     */ import okio.BufferedSource;
/*     */ import okio.ByteString;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000P\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\005\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\030\002\n\000\n\002\020\022\n\002\b\002\n\002\030\002\n\000\n\002\020\b\n\002\b\004\n\002\020\002\n\002\b\b\030\0002\0020\001:\001&B-\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007\022\006\020\b\032\0020\003\022\006\020\t\032\0020\003¢\006\002\020\nJ\b\020\036\032\0020\037H\026J\006\020 \032\0020\037J\b\020!\032\0020\037H\002J\b\020\"\032\0020\037H\002J\b\020#\032\0020\037H\002J\b\020$\032\0020\037H\002J\b\020%\032\0020\037H\002R\016\020\013\032\0020\003X\016¢\006\002\n\000R\016\020\f\032\0020\rX\004¢\006\002\n\000R\016\020\006\032\0020\007X\004¢\006\002\n\000R\016\020\016\032\0020\017X\016¢\006\002\n\000R\016\020\002\032\0020\003X\004¢\006\002\n\000R\016\020\020\032\0020\003X\016¢\006\002\n\000R\016\020\021\032\0020\003X\016¢\006\002\n\000R\020\020\022\032\004\030\0010\023X\004¢\006\002\n\000R\020\020\024\032\004\030\0010\025X\004¢\006\002\n\000R\016\020\026\032\0020\rX\004¢\006\002\n\000R\020\020\027\032\004\030\0010\030X\016¢\006\002\n\000R\016\020\t\032\0020\003X\004¢\006\002\n\000R\016\020\031\032\0020\032X\016¢\006\002\n\000R\016\020\b\032\0020\003X\004¢\006\002\n\000R\016\020\033\032\0020\003X\016¢\006\002\n\000R\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\034\020\035¨\006'"}, d2 = {"Lokhttp3/internal/ws/WebSocketReader;", "Ljava/io/Closeable;", "isClient", "", "source", "Lokio/BufferedSource;", "frameCallback", "Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", "perMessageDeflate", "noContextTakeover", "(ZLokio/BufferedSource;Lokhttp3/internal/ws/WebSocketReader$FrameCallback;ZZ)V", "closed", "controlFrameBuffer", "Lokio/Buffer;", "frameLength", "", "isControlFrame", "isFinalFrame", "maskCursor", "Lokio/Buffer$UnsafeCursor;", "maskKey", "", "messageFrameBuffer", "messageInflater", "Lokhttp3/internal/ws/MessageInflater;", "opcode", "", "readingCompressedMessage", "getSource", "()Lokio/BufferedSource;", "close", "", "processNextFrame", "readControlFrame", "readHeader", "readMessage", "readMessageFrame", "readUntilNonControlFrame", "FrameCallback", "okhttp"})
/*     */ public final class WebSocketReader
/*     */   implements Closeable
/*     */ {
/*     */   private boolean closed;
/*     */   private int opcode;
/*     */   private long frameLength;
/*     */   private boolean isFinalFrame;
/*     */   private boolean isControlFrame;
/*     */   private boolean readingCompressedMessage;
/*     */   private final Buffer controlFrameBuffer;
/*     */   private final Buffer messageFrameBuffer;
/*     */   private MessageInflater messageInflater;
/*     */   private final byte[] maskKey;
/*     */   private final Buffer.UnsafeCursor maskCursor;
/*     */   private final boolean isClient;
/*     */   @NotNull
/*     */   private final BufferedSource source;
/*     */   private final FrameCallback frameCallback;
/*     */   private final boolean perMessageDeflate;
/*     */   private final boolean noContextTakeover;
/*     */   
/*     */   public WebSocketReader(boolean isClient, @NotNull BufferedSource source, @NotNull FrameCallback frameCallback, boolean perMessageDeflate, boolean noContextTakeover) {
/*  54 */     this.isClient = isClient; this.source = source; this.frameCallback = frameCallback; this.perMessageDeflate = perMessageDeflate; this.noContextTakeover = noContextTakeover;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     this.controlFrameBuffer = new Buffer();
/*  71 */     this.messageFrameBuffer = new Buffer();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     this.maskKey = this.isClient ? null : new byte[4];
/*  78 */     this.maskCursor = this.isClient ? null : new Buffer.UnsafeCursor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final BufferedSource getSource() {
/*     */     return this.source;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void processNextFrame() throws IOException {
/* 102 */     readHeader();
/* 103 */     if (this.isControlFrame) {
/* 104 */       readControlFrame();
/*     */     } else {
/* 106 */       readMessageFrame();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private final void readHeader() throws IOException, ProtocolException {
/* 112 */     if (this.closed) throw (Throwable)new IOException("closed");
/*     */ 
/*     */     
/* 115 */     int b0 = 0;
/* 116 */     long timeoutBefore = this.source.timeout().timeoutNanos();
/* 117 */     this.source.timeout().clearTimeout();
/*     */     try {
/* 119 */       b0 = Util.and(this.source.readByte(), 255);
/*     */     } finally {
/* 121 */       this.source.timeout().timeout(timeoutBefore, TimeUnit.NANOSECONDS);
/*     */     } 
/*     */     
/* 124 */     this.opcode = b0 & 0xF;
/* 125 */     this.isFinalFrame = ((b0 & 0x80) != 0);
/* 126 */     this.isControlFrame = ((b0 & 0x8) != 0);
/*     */ 
/*     */     
/* 129 */     if (this.isControlFrame && !this.isFinalFrame) {
/* 130 */       throw (Throwable)new ProtocolException("Control frames must be final.");
/*     */     }
/*     */     
/* 133 */     boolean reservedFlag1 = ((b0 & 0x40) != 0);
/* 134 */     switch (this.opcode) {
/*     */       case 1:
/*     */       case 2:
/* 137 */         if (!this.perMessageDeflate) throw (Throwable)new ProtocolException("Unexpected rsv1 flag");
/*     */         
/*     */         this.readingCompressedMessage = reservedFlag1;
/*     */         break;
/*     */ 
/*     */       
/*     */       default:
/* 144 */         if (reservedFlag1) throw (Throwable)new ProtocolException("Unexpected rsv1 flag");
/*     */         
/*     */         break;
/*     */     } 
/* 148 */     boolean reservedFlag2 = ((b0 & 0x20) != 0);
/* 149 */     if (reservedFlag2) throw (Throwable)new ProtocolException("Unexpected rsv2 flag");
/*     */     
/* 151 */     boolean reservedFlag3 = ((b0 & 0x10) != 0);
/* 152 */     if (reservedFlag3) throw (Throwable)new ProtocolException("Unexpected rsv3 flag");
/*     */     
/* 154 */     int b1 = Util.and(this.source.readByte(), 255);
/*     */     
/* 156 */     boolean isMasked = ((b1 & 0x80) != 0);
/* 157 */     if (isMasked == this.isClient)
/*     */     {
/* 159 */       throw (Throwable)new ProtocolException(this.isClient ? 
/* 160 */           "Server-sent frames must not be masked." : 
/*     */           
/* 162 */           "Client-sent frames must be masked.");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 167 */     this.frameLength = (b1 & 0x7F);
/* 168 */     if (this.frameLength == 126L) {
/* 169 */       this.frameLength = Util.and(this.source.readShort(), 65535);
/* 170 */     } else if (this.frameLength == 127L) {
/* 171 */       this.frameLength = this.source.readLong();
/* 172 */       if (this.frameLength < 0L) {
/* 173 */         throw (Throwable)new ProtocolException(
/* 174 */             "Frame length 0x" + Util.toHexString(this.frameLength) + " > 0x7FFFFFFFFFFFFFFF");
/*     */       }
/*     */     } 
/*     */     
/* 178 */     if (this.isControlFrame && this.frameLength > 125L) {
/* 179 */       throw (Throwable)new ProtocolException("Control frame must be less than 125B.");
/*     */     }
/*     */     
/* 182 */     if (isMasked) {
/*     */       
/* 184 */       Intrinsics.checkNotNull(this.maskKey); this.source.readFully(this.maskKey);
/*     */     } 
/*     */   } private final void readControlFrame() throws IOException {
/*     */     int code;
/*     */     String reason;
/*     */     long bufferSize;
/* 190 */     if (this.frameLength > 0L) {
/* 191 */       this.source.readFully(this.controlFrameBuffer, this.frameLength);
/*     */       
/* 193 */       if (!this.isClient) {
/* 194 */         Intrinsics.checkNotNull(this.maskCursor); this.controlFrameBuffer.readAndWriteUnsafe(this.maskCursor);
/* 195 */         this.maskCursor.seek(0L);
/* 196 */         Intrinsics.checkNotNull(this.maskKey); WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
/* 197 */         this.maskCursor.close();
/*     */       } 
/*     */     } 
/*     */     
/* 201 */     switch (this.opcode) {
/*     */       case 9:
/* 203 */         this.frameCallback.onReadPing(this.controlFrameBuffer.readByteString());
/*     */         return;
/*     */       case 10:
/* 206 */         this.frameCallback.onReadPong(this.controlFrameBuffer.readByteString());
/*     */         return;
/*     */       case 8:
/* 209 */         code = 1005;
/* 210 */         reason = "";
/* 211 */         bufferSize = this.controlFrameBuffer.size();
/* 212 */         if (bufferSize == 1L)
/* 213 */           throw (Throwable)new ProtocolException("Malformed close payload length of 1."); 
/* 214 */         if (bufferSize != 0L) {
/* 215 */           code = this.controlFrameBuffer.readShort();
/* 216 */           reason = this.controlFrameBuffer.readUtf8();
/* 217 */           String codeExceptionMessage = WebSocketProtocol.INSTANCE.closeCodeExceptionMessage(code);
/* 218 */           if (codeExceptionMessage != null) throw (Throwable)new ProtocolException(codeExceptionMessage); 
/*     */         } 
/* 220 */         this.frameCallback.onReadClose(code, reason);
/* 221 */         this.closed = true;
/*     */         return;
/*     */     } 
/* 224 */     throw (Throwable)new ProtocolException("Unknown control opcode: " + Util.toHexString(this.opcode));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void readMessageFrame() throws IOException {
/* 231 */     int opcode = this.opcode;
/* 232 */     if (opcode != 1 && opcode != 2) {
/* 233 */       throw (Throwable)new ProtocolException("Unknown opcode: " + Util.toHexString(opcode));
/*     */     }
/*     */     
/* 236 */     readMessage();
/*     */     
/* 238 */     if (this.readingCompressedMessage) {
/* 239 */       if (this.messageInflater == null) {
/* 240 */         MessageInflater messageInflater1 = new MessageInflater(this.noContextTakeover); boolean bool1 = false, bool2 = false; MessageInflater it = messageInflater1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 299 */         int $i$a$-also-WebSocketReader$readMessageFrame$messageInflater$1 = 0;
/*     */         this.messageInflater = it;
/*     */       } 
/*     */       MessageInflater messageInflater = messageInflater1;
/*     */       messageInflater.inflate(this.messageFrameBuffer);
/*     */     } 
/*     */     if (opcode == 1) {
/*     */       this.frameCallback.onReadMessage(this.messageFrameBuffer.readUtf8());
/*     */     } else {
/*     */       this.frameCallback.onReadMessage(this.messageFrameBuffer.readByteString());
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void readUntilNonControlFrame() throws IOException {
/*     */     while (!this.closed) {
/*     */       readHeader();
/*     */       if (!this.isControlFrame)
/*     */         break; 
/*     */       readControlFrame();
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void readMessage() throws IOException {
/*     */     while (true) {
/*     */       if (this.closed)
/*     */         throw (Throwable)new IOException("closed"); 
/*     */       if (this.frameLength > 0L) {
/*     */         this.source.readFully(this.messageFrameBuffer, this.frameLength);
/*     */         if (!this.isClient) {
/*     */           Intrinsics.checkNotNull(this.maskCursor);
/*     */           this.messageFrameBuffer.readAndWriteUnsafe(this.maskCursor);
/*     */           this.maskCursor.seek(this.messageFrameBuffer.size() - this.frameLength);
/*     */           Intrinsics.checkNotNull(this.maskKey);
/*     */           WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
/*     */           this.maskCursor.close();
/*     */         } 
/*     */       } 
/*     */       if (this.isFinalFrame)
/*     */         break; 
/*     */       readUntilNonControlFrame();
/*     */       if (this.opcode != 0)
/*     */         throw (Throwable)new ProtocolException("Expected continuation opcode. Got: " + Util.toHexString(this.opcode)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*     */     if (this.messageInflater != null) {
/*     */       this.messageInflater.close();
/*     */     } else {
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000&\n\002\030\002\n\002\020\000\n\000\n\002\020\002\n\000\n\002\020\b\n\000\n\002\020\016\n\002\b\003\n\002\030\002\n\002\b\004\bf\030\0002\0020\001J\030\020\002\032\0020\0032\006\020\004\032\0020\0052\006\020\006\032\0020\007H&J\020\020\b\032\0020\0032\006\020\t\032\0020\007H&J\020\020\b\032\0020\0032\006\020\n\032\0020\013H&J\020\020\f\032\0020\0032\006\020\r\032\0020\013H&J\020\020\016\032\0020\0032\006\020\r\032\0020\013H&¨\006\017"}, d2 = {"Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", "", "onReadClose", "", "code", "", "reason", "", "onReadMessage", "text", "bytes", "Lokio/ByteString;", "onReadPing", "payload", "onReadPong", "okhttp"})
/*     */   public static interface FrameCallback {
/*     */     void onReadMessage(@NotNull String param1String) throws IOException;
/*     */     
/*     */     void onReadMessage(@NotNull ByteString param1ByteString) throws IOException;
/*     */     
/*     */     void onReadPing(@NotNull ByteString param1ByteString);
/*     */     
/*     */     void onReadPong(@NotNull ByteString param1ByteString);
/*     */     
/*     */     void onReadClose(int param1Int, @NotNull String param1String);
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/ws/WebSocketReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */