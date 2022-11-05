/*     */ package okhttp3.internal.ws;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.util.Random;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okio.Buffer;
/*     */ import okio.BufferedSink;
/*     */ import okio.ByteString;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000V\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\t\n\002\b\002\n\002\030\002\n\000\n\002\020\022\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\007\n\002\020\002\n\002\b\002\n\002\020\b\n\000\n\002\030\002\n\002\b\t\030\0002\0020\001B5\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007\022\006\020\b\032\0020\003\022\006\020\t\032\0020\003\022\006\020\n\032\0020\013¢\006\002\020\fJ\b\020\033\032\0020\034H\026J\030\020\035\032\0020\0342\006\020\036\032\0020\0372\b\020 \032\004\030\0010!J\030\020\"\032\0020\0342\006\020#\032\0020\0372\006\020$\032\0020!H\002J\026\020%\032\0020\0342\006\020&\032\0020\0372\006\020'\032\0020!J\016\020(\032\0020\0342\006\020$\032\0020!J\016\020)\032\0020\0342\006\020$\032\0020!R\016\020\002\032\0020\003X\004¢\006\002\n\000R\020\020\r\032\004\030\0010\016X\004¢\006\002\n\000R\020\020\017\032\004\030\0010\020X\004¢\006\002\n\000R\016\020\021\032\0020\022X\004¢\006\002\n\000R\020\020\023\032\004\030\0010\024X\016¢\006\002\n\000R\016\020\n\032\0020\013X\004¢\006\002\n\000R\016\020\t\032\0020\003X\004¢\006\002\n\000R\016\020\b\032\0020\003X\004¢\006\002\n\000R\021\020\006\032\0020\007¢\006\b\n\000\032\004\b\025\020\026R\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\027\020\030R\016\020\031\032\0020\022X\004¢\006\002\n\000R\016\020\032\032\0020\003X\016¢\006\002\n\000¨\006*"}, d2 = {"Lokhttp3/internal/ws/WebSocketWriter;", "Ljava/io/Closeable;", "isClient", "", "sink", "Lokio/BufferedSink;", "random", "Ljava/util/Random;", "perMessageDeflate", "noContextTakeover", "minimumDeflateSize", "", "(ZLokio/BufferedSink;Ljava/util/Random;ZZJ)V", "maskCursor", "Lokio/Buffer$UnsafeCursor;", "maskKey", "", "messageBuffer", "Lokio/Buffer;", "messageDeflater", "Lokhttp3/internal/ws/MessageDeflater;", "getRandom", "()Ljava/util/Random;", "getSink", "()Lokio/BufferedSink;", "sinkBuffer", "writerClosed", "close", "", "writeClose", "code", "", "reason", "Lokio/ByteString;", "writeControlFrame", "opcode", "payload", "writeMessageFrame", "formatOpcode", "data", "writePing", "writePong", "okhttp"})
/*     */ public final class WebSocketWriter
/*     */   implements Closeable
/*     */ {
/*     */   private final Buffer messageBuffer;
/*     */   private final Buffer sinkBuffer;
/*     */   private boolean writerClosed;
/*     */   private MessageDeflater messageDeflater;
/*     */   private final byte[] maskKey;
/*     */   private final Buffer.UnsafeCursor maskCursor;
/*     */   private final boolean isClient;
/*     */   @NotNull
/*     */   private final BufferedSink sink;
/*     */   @NotNull
/*     */   private final Random random;
/*     */   private final boolean perMessageDeflate;
/*     */   private final boolean noContextTakeover;
/*     */   private final long minimumDeflateSize;
/*     */   
/*     */   @NotNull
/*     */   public final BufferedSink getSink() {
/*     */     return this.sink;
/*     */   }
/*     */   
/*     */   public WebSocketWriter(boolean isClient, @NotNull BufferedSink sink, @NotNull Random random, boolean perMessageDeflate, boolean noContextTakeover, long minimumDeflateSize) {
/*  44 */     this.isClient = isClient; this.sink = sink; this.random = random; this.perMessageDeflate = perMessageDeflate; this.noContextTakeover = noContextTakeover; this.minimumDeflateSize = minimumDeflateSize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     this.messageBuffer = new Buffer();
/*     */ 
/*     */     
/*  56 */     this.sinkBuffer = this.sink.getBuffer();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     this.maskKey = this.isClient ? new byte[4] : null;
/*  64 */     this.maskCursor = this.isClient ? new Buffer.UnsafeCursor() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void writePing(@NotNull ByteString payload) throws IOException {
/*  69 */     Intrinsics.checkNotNullParameter(payload, "payload"); writeControlFrame(9, payload);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void writePong(@NotNull ByteString payload) throws IOException {
/*  75 */     Intrinsics.checkNotNullParameter(payload, "payload"); writeControlFrame(10, payload);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final Random getRandom() {
/*     */     return this.random;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void writeClose(int code, @Nullable ByteString reason) throws IOException {
/*  87 */     ByteString payload = ByteString.EMPTY;
/*  88 */     if (code != 0 || reason != null) {
/*  89 */       if (code != 0) {
/*  90 */         WebSocketProtocol.INSTANCE.validateCloseCode(code);
/*     */       }
/*  92 */       Buffer buffer1 = new Buffer(); boolean bool1 = false, bool2 = false; Buffer $this$run = buffer1; int $i$a$-run-WebSocketWriter$writeClose$1 = 0;
/*  93 */       $this$run.writeShort(code);
/*  94 */       if (reason != null) {
/*  95 */         $this$run.write(reason);
/*     */       }
/*  97 */       payload = $this$run.readByteString();
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 102 */       writeControlFrame(8, payload);
/*     */     } finally {
/* 104 */       this.writerClosed = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private final void writeControlFrame(int opcode, ByteString payload) throws IOException {
/* 110 */     if (this.writerClosed) throw (Throwable)new IOException("closed");
/*     */     
/* 112 */     int length = payload.size();
/* 113 */     boolean bool1 = (length <= 125L) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-require-WebSocketWriter$writeControlFrame$1 = 0; String str = 
/* 114 */         "Payload size must be less than or equal to 125";
/*     */       throw (Throwable)new IllegalArgumentException(str.toString()); }
/*     */     
/* 117 */     int b0 = 0x80 | opcode;
/* 118 */     this.sinkBuffer.writeByte(b0);
/*     */     
/* 120 */     int b1 = length;
/* 121 */     if (this.isClient) {
/* 122 */       b1 |= 0x80;
/* 123 */       this.sinkBuffer.writeByte(b1);
/*     */       
/* 125 */       Intrinsics.checkNotNull(this.maskKey); this.random.nextBytes(this.maskKey);
/* 126 */       this.sinkBuffer.write(this.maskKey);
/*     */       
/* 128 */       if (length > 0) {
/* 129 */         long payloadStart = this.sinkBuffer.size();
/* 130 */         this.sinkBuffer.write(payload);
/*     */         
/* 132 */         Intrinsics.checkNotNull(this.maskCursor); this.sinkBuffer.readAndWriteUnsafe(this.maskCursor);
/* 133 */         this.maskCursor.seek(payloadStart);
/* 134 */         WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
/* 135 */         this.maskCursor.close();
/*     */       } 
/*     */     } else {
/* 138 */       this.sinkBuffer.writeByte(b1);
/* 139 */       this.sinkBuffer.write(payload);
/*     */     } 
/*     */     
/* 142 */     this.sink.flush();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void writeMessageFrame(int formatOpcode, @NotNull ByteString data) throws IOException {
/* 147 */     Intrinsics.checkNotNullParameter(data, "data"); if (this.writerClosed) throw (Throwable)new IOException("closed");
/*     */     
/* 149 */     this.messageBuffer.write(data);
/*     */     
/* 151 */     int b0 = formatOpcode | 0x80;
/* 152 */     if (this.perMessageDeflate && data.size() >= this.minimumDeflateSize) {
/* 153 */       if (this.messageDeflater == null) {
/* 154 */         MessageDeflater messageDeflater1 = new MessageDeflater(this.noContextTakeover); boolean bool1 = false, bool2 = false; MessageDeflater it = messageDeflater1;
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
/* 203 */         int $i$a$-also-WebSocketWriter$writeMessageFrame$messageDeflater$1 = 0;
/*     */         this.messageDeflater = it;
/*     */       } 
/*     */       MessageDeflater messageDeflater = messageDeflater1;
/*     */       messageDeflater.deflate(this.messageBuffer);
/*     */       b0 |= 0x40;
/*     */     } 
/*     */     long dataSize = this.messageBuffer.size();
/*     */     this.sinkBuffer.writeByte(b0);
/*     */     int b1 = 0;
/*     */     if (this.isClient)
/*     */       b1 |= 0x80; 
/*     */     if (dataSize <= 125L) {
/*     */       b1 |= (int)dataSize;
/*     */       this.sinkBuffer.writeByte(b1);
/*     */     } else if (dataSize <= 65535L) {
/*     */       b1 |= 0x7E;
/*     */       this.sinkBuffer.writeByte(b1);
/*     */       this.sinkBuffer.writeShort((int)dataSize);
/*     */     } else {
/*     */       b1 |= 0x7F;
/*     */       this.sinkBuffer.writeByte(b1);
/*     */       this.sinkBuffer.writeLong(dataSize);
/*     */     } 
/*     */     if (this.isClient) {
/*     */       Intrinsics.checkNotNull(this.maskKey);
/*     */       this.random.nextBytes(this.maskKey);
/*     */       this.sinkBuffer.write(this.maskKey);
/*     */       if (dataSize > 0L) {
/*     */         Intrinsics.checkNotNull(this.maskCursor);
/*     */         this.messageBuffer.readAndWriteUnsafe(this.maskCursor);
/*     */         this.maskCursor.seek(0L);
/*     */         WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
/*     */         this.maskCursor.close();
/*     */       } 
/*     */     } 
/*     */     this.sinkBuffer.write(this.messageBuffer, dataSize);
/*     */     this.sink.emit();
/*     */   }
/*     */   
/*     */   public void close() {
/*     */     if (this.messageDeflater != null) {
/*     */       this.messageDeflater.close();
/*     */     } else {
/*     */     
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/ws/WebSocketWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */