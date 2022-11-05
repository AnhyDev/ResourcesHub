/*     */ package okhttp3.internal.http2;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.internal.Util;
/*     */ import okio.Buffer;
/*     */ import okio.BufferedSink;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\\\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\b\n\000\n\002\020\002\n\000\n\002\030\002\n\002\b\021\n\002\030\002\n\000\n\002\020\022\n\002\b\002\n\002\020 \n\002\030\002\n\002\b\f\n\002\020\t\n\002\b\003\030\000 :2\0020\001:\001:B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005¢\006\002\020\006J\016\020\020\032\0020\0212\006\020\022\032\0020\023J\b\020\024\032\0020\021H\026J\006\020\025\032\0020\021J(\020\026\032\0020\0212\006\020\027\032\0020\0052\006\020\030\032\0020\0172\b\020\031\032\004\030\0010\t2\006\020\032\032\0020\017J(\020\033\032\0020\0212\006\020\030\032\0020\0172\006\020\034\032\0020\0172\b\020\035\032\004\030\0010\t2\006\020\032\032\0020\017J\006\020\036\032\0020\021J&\020\037\032\0020\0212\006\020\030\032\0020\0172\006\020 \032\0020\0172\006\020!\032\0020\0172\006\020\034\032\0020\017J\036\020\"\032\0020\0212\006\020#\032\0020\0172\006\020$\032\0020%2\006\020&\032\0020'J$\020(\032\0020\0212\006\020\027\032\0020\0052\006\020\030\032\0020\0172\f\020)\032\b\022\004\022\0020+0*J\006\020,\032\0020\017J\036\020-\032\0020\0212\006\020.\032\0020\0052\006\020/\032\0020\0172\006\0200\032\0020\017J$\0201\032\0020\0212\006\020\030\032\0020\0172\006\0202\032\0020\0172\f\0203\032\b\022\004\022\0020+0*J\026\0204\032\0020\0212\006\020\030\032\0020\0172\006\020$\032\0020%J\016\0205\032\0020\0212\006\0205\032\0020\023J\026\0206\032\0020\0212\006\020\030\032\0020\0172\006\0207\032\00208J\030\0209\032\0020\0212\006\020\030\032\0020\0172\006\020\032\032\00208H\002R\016\020\004\032\0020\005X\004¢\006\002\n\000R\016\020\007\032\0020\005X\016¢\006\002\n\000R\016\020\b\032\0020\tX\004¢\006\002\n\000R\021\020\n\032\0020\013¢\006\b\n\000\032\004\b\f\020\rR\016\020\016\032\0020\017X\016¢\006\002\n\000R\016\020\002\032\0020\003X\004¢\006\002\n\000¨\006;"}, d2 = {"Lokhttp3/internal/http2/Http2Writer;", "Ljava/io/Closeable;", "sink", "Lokio/BufferedSink;", "client", "", "(Lokio/BufferedSink;Z)V", "closed", "hpackBuffer", "Lokio/Buffer;", "hpackWriter", "Lokhttp3/internal/http2/Hpack$Writer;", "getHpackWriter", "()Lokhttp3/internal/http2/Hpack$Writer;", "maxFrameSize", "", "applyAndAckSettings", "", "peerSettings", "Lokhttp3/internal/http2/Settings;", "close", "connectionPreface", "data", "outFinished", "streamId", "source", "byteCount", "dataFrame", "flags", "buffer", "flush", "frameHeader", "length", "type", "goAway", "lastGoodStreamId", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "debugData", "", "headers", "headerBlock", "", "Lokhttp3/internal/http2/Header;", "maxDataLength", "ping", "ack", "payload1", "payload2", "pushPromise", "promisedStreamId", "requestHeaders", "rstStream", "settings", "windowUpdate", "windowSizeIncrement", "", "writeContinuationFrames", "Companion", "okhttp"})
/*     */ public final class Http2Writer
/*     */   implements Closeable
/*     */ {
/*     */   private final Buffer hpackBuffer;
/*     */   private int maxFrameSize;
/*     */   private boolean closed;
/*     */   @NotNull
/*     */   private final Hpack.Writer hpackWriter;
/*     */   private final BufferedSink sink;
/*     */   private final boolean client;
/*     */   private static final Logger logger;
/*     */   
/*     */   public Http2Writer(@NotNull BufferedSink sink, boolean client) {
/*  45 */     this.sink = sink; this.client = client;
/*     */ 
/*     */ 
/*     */     
/*  49 */     this.hpackBuffer = new Buffer();
/*  50 */     this.maxFrameSize = 16384;
/*     */     
/*  52 */     this.hpackWriter = new Hpack.Writer(0, false, this.hpackBuffer, 3, null); } @NotNull public final Hpack.Writer getHpackWriter() { return this.hpackWriter; }
/*     */ 
/*     */   
/*     */   public final synchronized void connectionPreface() throws IOException {
/*  56 */     if (this.closed) throw (Throwable)new IOException("closed"); 
/*  57 */     if (!this.client)
/*  58 */       return;  if (logger.isLoggable(Level.FINE)) {
/*  59 */       logger.fine(Util.format(">> CONNECTION " + Http2.CONNECTION_PREFACE.hex(), new Object[0]));
/*     */     }
/*  61 */     this.sink.write(Http2.CONNECTION_PREFACE);
/*  62 */     this.sink.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final synchronized void applyAndAckSettings(@NotNull Settings peerSettings) throws IOException {
/*  68 */     Intrinsics.checkNotNullParameter(peerSettings, "peerSettings"); if (this.closed) throw (Throwable)new IOException("closed"); 
/*  69 */     this.maxFrameSize = peerSettings.getMaxFrameSize(this.maxFrameSize);
/*  70 */     if (peerSettings.getHeaderTableSize() != -1) {
/*  71 */       this.hpackWriter.resizeHeaderTable(peerSettings.getHeaderTableSize());
/*     */     }
/*  73 */     frameHeader(
/*  74 */         0, 
/*  75 */         0, 
/*  76 */         4, 
/*  77 */         1);
/*     */     
/*  79 */     this.sink.flush();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final synchronized void pushPromise(int streamId, int promisedStreamId, @NotNull List<Header> requestHeaders) throws IOException {
/* 100 */     Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders"); if (this.closed) throw (Throwable)new IOException("closed"); 
/* 101 */     this.hpackWriter.writeHeaders(requestHeaders);
/*     */     
/* 103 */     long byteCount = this.hpackBuffer.size();
/* 104 */     long l1 = this.maxFrameSize - 4L; boolean bool = false; int length = (int)Math.min(l1, byteCount);
/* 105 */     frameHeader(
/* 106 */         streamId, 
/* 107 */         length + 4, 
/* 108 */         5, 
/* 109 */         (byteCount == length) ? 4 : 0);
/*     */     
/* 111 */     this.sink.writeInt(promisedStreamId & Integer.MAX_VALUE);
/* 112 */     this.sink.write(this.hpackBuffer, length);
/*     */     
/* 114 */     if (byteCount > length) writeContinuationFrames(streamId, byteCount - length);
/*     */   
/*     */   }
/*     */   
/*     */   public final synchronized void flush() throws IOException {
/* 119 */     if (this.closed) throw (Throwable)new IOException("closed"); 
/* 120 */     this.sink.flush();
/*     */   }
/*     */ 
/*     */   
/*     */   public final synchronized void rstStream(int streamId, @NotNull ErrorCode errorCode) throws IOException {
/* 125 */     Intrinsics.checkNotNullParameter(errorCode, "errorCode"); if (this.closed) throw (Throwable)new IOException("closed"); 
/* 126 */     boolean bool1 = (errorCode.getHttpCode() != -1) ? true : false, bool2 = false, bool3 = false; bool3 = false; boolean bool4 = false; if (!bool1) { boolean bool = false; String str = "Failed requirement."; throw (Throwable)new IllegalArgumentException(str.toString()); }
/*     */     
/* 128 */     frameHeader(
/* 129 */         streamId, 
/* 130 */         4, 
/* 131 */         3, 
/* 132 */         0);
/*     */     
/* 134 */     this.sink.writeInt(errorCode.getHttpCode());
/* 135 */     this.sink.flush();
/*     */   }
/*     */   
/*     */   public final int maxDataLength() {
/* 139 */     return this.maxFrameSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final synchronized void data(boolean outFinished, int streamId, @Nullable Buffer source, int byteCount) throws IOException {
/* 150 */     if (this.closed) throw (Throwable)new IOException("closed"); 
/* 151 */     int flags = 0;
/* 152 */     if (outFinished) flags |= 0x1; 
/* 153 */     dataFrame(streamId, flags, source, byteCount);
/*     */   }
/*     */ 
/*     */   
/*     */   public final void dataFrame(int streamId, int flags, @Nullable Buffer buffer, int byteCount) throws IOException {
/* 158 */     frameHeader(
/* 159 */         streamId, 
/* 160 */         byteCount, 
/* 161 */         0, 
/* 162 */         flags);
/*     */     
/* 164 */     if (byteCount > 0) {
/* 165 */       Intrinsics.checkNotNull(buffer); this.sink.write(buffer, byteCount);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final synchronized void settings(@NotNull Settings settings) throws IOException {
/* 172 */     Intrinsics.checkNotNullParameter(settings, "settings"); if (this.closed) throw (Throwable)new IOException("closed"); 
/* 173 */     frameHeader(
/* 174 */         0, 
/* 175 */         settings.size() * 6, 
/* 176 */         4, 
/* 177 */         0);
/*     */     
/* 179 */     for (byte b1 = 0, b2 = 10; b1 < b2; b1++) {
/* 180 */       if (settings.isSet(b1)) {
/* 181 */         switch (b1) { case 4: case 7: default: break; }  int id = 
/*     */ 
/*     */           
/* 184 */           b1;
/*     */         
/* 186 */         this.sink.writeShort(id);
/* 187 */         this.sink.writeInt(settings.get(b1));
/*     */       } 
/* 189 */     }  this.sink.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final synchronized void ping(boolean ack, int payload1, int payload2) throws IOException {
/* 198 */     if (this.closed) throw (Throwable)new IOException("closed"); 
/* 199 */     frameHeader(
/* 200 */         0, 
/* 201 */         8, 
/* 202 */         6, 
/* 203 */         ack ? 1 : 0);
/*     */     
/* 205 */     this.sink.writeInt(payload1);
/* 206 */     this.sink.writeInt(payload2);
/* 207 */     this.sink.flush();
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
/*     */   public final synchronized void goAway(int lastGoodStreamId, @NotNull ErrorCode errorCode, @NotNull byte[] debugData) throws IOException
/*     */   {
/* 220 */     Intrinsics.checkNotNullParameter(errorCode, "errorCode"); Intrinsics.checkNotNullParameter(debugData, "debugData"); if (this.closed) throw (Throwable)new IOException("closed"); 
/* 221 */     boolean bool1 = (errorCode.getHttpCode() != -1) ? true : false, bool2 = false, bool3 = false; if (!bool1)
/*     */     
/*     */     { 
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
/* 318 */       int $i$a$-require-Http2Writer$goAway$1 = 0; String str = "errorCode.httpCode == -1"; throw (Throwable)new IllegalArgumentException(str.toString()); }  frameHeader(0, 8 + debugData.length, 7, 0); this.sink.writeInt(lastGoodStreamId); this.sink.writeInt(errorCode.getHttpCode()); byte[] arrayOfByte1 = debugData; bool2 = false; byte[] arrayOfByte2 = arrayOfByte1; boolean bool4 = false; if (!((arrayOfByte2.length == 0) ? 1 : 0)) this.sink.write(debugData);  this.sink.flush(); } public final void frameHeader(int streamId, int length, int type, int flags) throws IOException { if (logger.isLoggable(Level.FINE)) logger.fine(Http2.INSTANCE.frameLog(false, streamId, length, type, flags));  boolean bool1 = (length <= this.maxFrameSize) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-require-Http2Writer$frameHeader$1 = 0; String str = "FRAME_SIZE_ERROR length > " + this.maxFrameSize + ": " + length; throw (Throwable)new IllegalArgumentException(str.toString()); }  bool1 = ((streamId & (int)2147483648L) == 0) ? true : false; bool2 = false; bool3 = false; if (!bool1) { int $i$a$-require-Http2Writer$frameHeader$2 = 0;
/*     */       String str = "reserved bit set: " + streamId;
/*     */       throw (Throwable)new IllegalArgumentException(str.toString()); }
/*     */     
/*     */     Util.writeMedium(this.sink, length);
/*     */     this.sink.writeByte(type & 0xFF);
/*     */     this.sink.writeByte(flags & 0xFF);
/*     */     this.sink.writeInt(streamId & Integer.MAX_VALUE); }
/*     */ 
/*     */   
/*     */   public final synchronized void windowUpdate(int streamId, long windowSizeIncrement) throws IOException {
/*     */     if (this.closed)
/*     */       throw (Throwable)new IOException("closed"); 
/*     */     boolean bool1 = (windowSizeIncrement != 0L && windowSizeIncrement <= 2147483647L) ? true : false, bool2 = false, bool3 = false;
/*     */     if (!bool1) {
/*     */       int $i$a$-require-Http2Writer$windowUpdate$1 = 0;
/*     */       String str = "windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: " + windowSizeIncrement;
/*     */       throw (Throwable)new IllegalArgumentException(str.toString());
/*     */     } 
/*     */     frameHeader(streamId, 4, 8, 0);
/*     */     this.sink.writeInt((int)windowSizeIncrement);
/*     */     this.sink.flush();
/*     */   }
/*     */   
/*     */   public synchronized void close() throws IOException {
/*     */     this.closed = true;
/*     */     this.sink.close();
/*     */   }
/*     */   
/*     */   private final void writeContinuationFrames(int streamId, long byteCount) throws IOException {
/*     */     long l = byteCount;
/*     */     while (l > 0L) {
/*     */       long l1 = this.maxFrameSize;
/*     */       boolean bool = false;
/*     */       long length = Math.min(l1, l);
/*     */       l -= length;
/*     */       frameHeader(streamId, (int)length, 9, (l == 0L) ? 4 : 0);
/*     */       this.sink.write(this.hpackBuffer, length);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final synchronized void headers(boolean outFinished, int streamId, @NotNull List<Header> headerBlock) throws IOException {
/*     */     Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
/*     */     if (this.closed)
/*     */       throw (Throwable)new IOException("closed"); 
/*     */     this.hpackWriter.writeHeaders(headerBlock);
/*     */     long byteCount = this.hpackBuffer.size();
/*     */     long l1 = this.maxFrameSize;
/*     */     boolean bool = false;
/*     */     long length = Math.min(l1, byteCount);
/*     */     int flags = (byteCount == length) ? 4 : 0;
/*     */     if (outFinished)
/*     */       flags |= 0x1; 
/*     */     frameHeader(streamId, (int)length, 1, flags);
/*     */     this.sink.write(this.hpackBuffer, length);
/*     */     if (byteCount > length)
/*     */       writeContinuationFrames(streamId, byteCount - length); 
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\n \005*\004\030\0010\0040\004X\004¢\006\002\n\000¨\006\006"}, d2 = {"Lokhttp3/internal/http2/Http2Writer$Companion;", "", "()V", "logger", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */   }
/*     */   
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   static {
/*     */     logger = Logger.getLogger(Http2.class.getName());
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http2/Http2Writer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */