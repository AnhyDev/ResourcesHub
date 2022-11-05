/*     */ package okhttp3.internal.http2;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.ranges.IntProgression;
/*     */ import kotlin.ranges.RangesKt;
/*     */ import okhttp3.internal.Util;
/*     */ import okio.Buffer;
/*     */ import okio.BufferedSource;
/*     */ import okio.ByteString;
/*     */ import okio.Source;
/*     */ import okio.Timeout;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000H\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\020\b\n\002\b\004\n\002\020 \n\002\030\002\n\002\b\f\030\000 #2\0020\001:\003#$%B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005¢\006\002\020\006J\b\020\013\032\0020\fH\026J\026\020\r\032\0020\0052\006\020\016\032\0020\0052\006\020\017\032\0020\020J\016\020\021\032\0020\f2\006\020\017\032\0020\020J(\020\022\032\0020\f2\006\020\017\032\0020\0202\006\020\023\032\0020\0242\006\020\025\032\0020\0242\006\020\026\032\0020\024H\002J(\020\027\032\0020\f2\006\020\017\032\0020\0202\006\020\023\032\0020\0242\006\020\025\032\0020\0242\006\020\026\032\0020\024H\002J.\020\030\032\b\022\004\022\0020\0320\0312\006\020\023\032\0020\0242\006\020\033\032\0020\0242\006\020\025\032\0020\0242\006\020\026\032\0020\024H\002J(\020\034\032\0020\f2\006\020\017\032\0020\0202\006\020\023\032\0020\0242\006\020\025\032\0020\0242\006\020\026\032\0020\024H\002J(\020\035\032\0020\f2\006\020\017\032\0020\0202\006\020\023\032\0020\0242\006\020\025\032\0020\0242\006\020\026\032\0020\024H\002J\030\020\036\032\0020\f2\006\020\017\032\0020\0202\006\020\026\032\0020\024H\002J(\020\036\032\0020\f2\006\020\017\032\0020\0202\006\020\023\032\0020\0242\006\020\025\032\0020\0242\006\020\026\032\0020\024H\002J(\020\037\032\0020\f2\006\020\017\032\0020\0202\006\020\023\032\0020\0242\006\020\025\032\0020\0242\006\020\026\032\0020\024H\002J(\020 \032\0020\f2\006\020\017\032\0020\0202\006\020\023\032\0020\0242\006\020\025\032\0020\0242\006\020\026\032\0020\024H\002J(\020!\032\0020\f2\006\020\017\032\0020\0202\006\020\023\032\0020\0242\006\020\025\032\0020\0242\006\020\026\032\0020\024H\002J(\020\"\032\0020\f2\006\020\017\032\0020\0202\006\020\023\032\0020\0242\006\020\025\032\0020\0242\006\020\026\032\0020\024H\002R\016\020\004\032\0020\005X\004¢\006\002\n\000R\016\020\007\032\0020\bX\004¢\006\002\n\000R\016\020\t\032\0020\nX\004¢\006\002\n\000R\016\020\002\032\0020\003X\004¢\006\002\n\000¨\006&"}, d2 = {"Lokhttp3/internal/http2/Http2Reader;", "Ljava/io/Closeable;", "source", "Lokio/BufferedSource;", "client", "", "(Lokio/BufferedSource;Z)V", "continuation", "Lokhttp3/internal/http2/Http2Reader$ContinuationSource;", "hpackReader", "Lokhttp3/internal/http2/Hpack$Reader;", "close", "", "nextFrame", "requireSettings", "handler", "Lokhttp3/internal/http2/Http2Reader$Handler;", "readConnectionPreface", "readData", "length", "", "flags", "streamId", "readGoAway", "readHeaderBlock", "", "Lokhttp3/internal/http2/Header;", "padding", "readHeaders", "readPing", "readPriority", "readPushPromise", "readRstStream", "readSettings", "readWindowUpdate", "Companion", "ContinuationSource", "Handler", "okhttp"})
/*     */ public final class Http2Reader
/*     */   implements Closeable
/*     */ {
/*     */   private final ContinuationSource continuation;
/*     */   private final Hpack.Reader hpackReader;
/*     */   private final BufferedSource source;
/*     */   private final boolean client;
/*     */   @NotNull
/*     */   private static final Logger logger;
/*     */   
/*     */   public Http2Reader(@NotNull BufferedSource source, boolean client) {
/*  58 */     this.source = source; this.client = client;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     this.continuation = new ContinuationSource(this.source);
/*  64 */     this.hpackReader = new Hpack.Reader(
/*  65 */         this.continuation, 
/*  66 */         4096, 0, 4, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public final void readConnectionPreface(@NotNull Handler handler) throws IOException {
/*  71 */     Intrinsics.checkNotNullParameter(handler, "handler"); if (this.client) {
/*     */       
/*  73 */       if (!nextFrame(true, handler)) {
/*  74 */         throw (Throwable)new IOException("Required SETTINGS preface not received");
/*     */       }
/*     */     } else {
/*     */       
/*  78 */       ByteString connectionPreface = this.source.readByteString(Http2.CONNECTION_PREFACE.size());
/*  79 */       if (logger.isLoggable(Level.FINE)) logger.fine(Util.format("<< CONNECTION " + connectionPreface.hex(), new Object[0])); 
/*  80 */       if ((Intrinsics.areEqual(Http2.CONNECTION_PREFACE, connectionPreface) ^ true) != 0) {
/*  81 */         throw (Throwable)new IOException("Expected a connection header but was " + connectionPreface.utf8());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean nextFrame(boolean requireSettings, @NotNull Handler handler) throws IOException {
/*  88 */     Intrinsics.checkNotNullParameter(handler, "handler"); try {
/*  89 */       this.source.require(9L);
/*  90 */     } catch (EOFException e) {
/*  91 */       return false;
/*     */     } 
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
/* 105 */     int length = Util.readMedium(this.source);
/* 106 */     if (length > 16384) {
/* 107 */       throw (Throwable)new IOException("FRAME_SIZE_ERROR: " + length);
/*     */     }
/* 109 */     int type = Util.and(this.source.readByte(), 255);
/* 110 */     int flags = Util.and(this.source.readByte(), 255);
/* 111 */     int streamId = this.source.readInt() & Integer.MAX_VALUE;
/* 112 */     if (logger.isLoggable(Level.FINE)) logger.fine(Http2.INSTANCE.frameLog(true, streamId, length, type, flags));
/*     */     
/* 114 */     if (requireSettings && type != 4) {
/* 115 */       throw (Throwable)new IOException("Expected a SETTINGS frame but was " + Http2.INSTANCE.formattedType$okhttp(type));
/*     */     }
/*     */     
/* 118 */     switch (type) { case 0:
/* 119 */         readData(handler, length, flags, streamId);
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
/* 131 */         return true;case 1: readHeaders(handler, length, flags, streamId); return true;case 2: readPriority(handler, length, flags, streamId); return true;case 3: readRstStream(handler, length, flags, streamId); return true;case 4: readSettings(handler, length, flags, streamId); return true;case 5: readPushPromise(handler, length, flags, streamId); return true;case 6: readPing(handler, length, flags, streamId); return true;case 7: readGoAway(handler, length, flags, streamId); return true;case 8: readWindowUpdate(handler, length, flags, streamId); return true; }  this.source.skip(length); return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private final void readHeaders(Handler handler, int length, int flags, int streamId) throws IOException {
/* 136 */     if (streamId == 0) throw (Throwable)new IOException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0");
/*     */     
/* 138 */     boolean endStream = ((flags & 0x1) != 0);
/* 139 */     int padding = ((flags & 0x8) != 0) ? Util.and(this.source.readByte(), 255) : 0;
/*     */     
/* 141 */     int headerBlockLength = length;
/* 142 */     if ((flags & 0x20) != 0) {
/* 143 */       readPriority(handler, streamId);
/* 144 */       headerBlockLength -= 5;
/*     */     } 
/* 146 */     headerBlockLength = Companion.lengthWithoutPadding(headerBlockLength, flags, padding);
/* 147 */     List<Header> headerBlock = readHeaderBlock(headerBlockLength, padding, flags, streamId);
/*     */     
/* 149 */     handler.headers(endStream, streamId, -1, headerBlock);
/*     */   }
/*     */ 
/*     */   
/*     */   private final List<Header> readHeaderBlock(int length, int padding, int flags, int streamId) throws IOException {
/* 154 */     this.continuation.setLeft(length);
/* 155 */     this.continuation.setLength(this.continuation.getLeft());
/* 156 */     this.continuation.setPadding(padding);
/* 157 */     this.continuation.setFlags(flags);
/* 158 */     this.continuation.setStreamId(streamId);
/*     */ 
/*     */ 
/*     */     
/* 162 */     this.hpackReader.readHeaders();
/* 163 */     return this.hpackReader.getAndResetHeaderList();
/*     */   }
/*     */ 
/*     */   
/*     */   private final void readData(Handler handler, int length, int flags, int streamId) throws IOException {
/* 168 */     if (streamId == 0) throw (Throwable)new IOException("PROTOCOL_ERROR: TYPE_DATA streamId == 0");
/*     */ 
/*     */     
/* 171 */     boolean inFinished = ((flags & 0x1) != 0);
/* 172 */     boolean gzipped = ((flags & 0x20) != 0);
/* 173 */     if (gzipped) {
/* 174 */       throw (Throwable)new IOException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA");
/*     */     }
/*     */     
/* 177 */     int padding = ((flags & 0x8) != 0) ? Util.and(this.source.readByte(), 255) : 0;
/* 178 */     int dataLength = Companion.lengthWithoutPadding(length, flags, padding);
/*     */     
/* 180 */     handler.data(inFinished, streamId, this.source, dataLength);
/* 181 */     this.source.skip(padding);
/*     */   }
/*     */ 
/*     */   
/*     */   private final void readPriority(Handler handler, int length, int flags, int streamId) throws IOException {
/* 186 */     if (length != 5) throw (Throwable)new IOException("TYPE_PRIORITY length: " + length + " != 5"); 
/* 187 */     if (streamId == 0) throw (Throwable)new IOException("TYPE_PRIORITY streamId == 0"); 
/* 188 */     readPriority(handler, streamId);
/*     */   }
/*     */ 
/*     */   
/*     */   private final void readPriority(Handler handler, int streamId) throws IOException {
/* 193 */     int w1 = this.source.readInt();
/* 194 */     boolean exclusive = ((w1 & (int)2147483648L) != 0);
/* 195 */     int streamDependency = w1 & Integer.MAX_VALUE;
/* 196 */     int weight = Util.and(this.source.readByte(), 255) + 1;
/* 197 */     handler.priority(streamId, streamDependency, weight, exclusive);
/*     */   }
/*     */   
/*     */   private final void readRstStream(Handler handler, int length, int flags, int streamId) throws IOException
/*     */   {
/* 202 */     if (length != 4) throw (Throwable)new IOException("TYPE_RST_STREAM length: " + length + " != 4"); 
/* 203 */     if (streamId == 0) throw (Throwable)new IOException("TYPE_RST_STREAM streamId == 0"); 
/* 204 */     int errorCodeInt = this.source.readInt();
/* 205 */     if (ErrorCode.Companion.fromHttp2(errorCodeInt) != null) { ErrorCode errorCode = ErrorCode.Companion.fromHttp2(errorCodeInt);
/*     */       
/* 207 */       handler.rstStream(streamId, errorCode);
/*     */       return; }
/*     */     
/*     */     ErrorCode.Companion.fromHttp2(errorCodeInt);
/*     */     throw (Throwable)new IOException("TYPE_RST_STREAM unexpected error code: " + errorCodeInt); } private final void readSettings(Handler handler, int length, int flags, int streamId) throws IOException {
/* 212 */     if (streamId != 0) throw (Throwable)new IOException("TYPE_SETTINGS streamId != 0"); 
/* 213 */     if ((flags & 0x1) != 0) {
/* 214 */       if (length != 0) throw (Throwable)new IOException("FRAME_SIZE_ERROR ack frame should be empty!"); 
/* 215 */       handler.ackSettings();
/*     */       
/*     */       return;
/*     */     } 
/* 219 */     if (length % 6 != 0) throw (Throwable)new IOException("TYPE_SETTINGS length % 6 != 0: " + length); 
/* 220 */     Settings settings = new Settings();
/* 221 */     int i = RangesKt.step((IntProgression)RangesKt.until(0, length), 6).getFirst(), j = RangesKt.step((IntProgression)RangesKt.until(0, length), 6).getLast(), k = RangesKt.step((IntProgression)RangesKt.until(0, length), 6).getStep(); if ((k >= 0) ? (i <= j) : (i >= j))
/* 222 */       while (true) { int id = Util.and(this.source.readShort(), 65535);
/* 223 */         int value = this.source.readInt();
/*     */         
/* 225 */         switch (id) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           case 2:
/* 232 */             if (value != 0 && value != 1) {
/* 233 */               throw (Throwable)new IOException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1");
/*     */             }
/*     */             break;
/*     */           
/*     */           case 3:
/* 238 */             id = 4;
/*     */             break;
/*     */           
/*     */           case 4:
/* 242 */             id = 7;
/* 243 */             if (value < 0) {
/* 244 */               throw (Throwable)new IOException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1");
/*     */             }
/*     */             break;
/*     */ 
/*     */           
/*     */           case 5:
/* 250 */             if (value < 16384 || value > 16777215) {
/* 251 */               throw (Throwable)new IOException("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: " + value);
/*     */             }
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 263 */         settings.set(id, value); if (i != j) { int m = i + k; continue; }
/*     */          break; }
/* 265 */         handler.settings(false, settings);
/*     */   }
/*     */ 
/*     */   
/*     */   private final void readPushPromise(Handler handler, int length, int flags, int streamId) throws IOException {
/* 270 */     if (streamId == 0) {
/* 271 */       throw (Throwable)new IOException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0");
/*     */     }
/* 273 */     int padding = ((flags & 0x8) != 0) ? Util.and(this.source.readByte(), 255) : 0;
/* 274 */     int promisedStreamId = this.source.readInt() & Integer.MAX_VALUE;
/* 275 */     int headerBlockLength = Companion.lengthWithoutPadding(length - 4, flags, padding);
/* 276 */     List<Header> headerBlock = readHeaderBlock(headerBlockLength, padding, flags, streamId);
/* 277 */     handler.pushPromise(streamId, promisedStreamId, headerBlock);
/*     */   }
/*     */ 
/*     */   
/*     */   private final void readPing(Handler handler, int length, int flags, int streamId) throws IOException {
/* 282 */     if (length != 8) throw (Throwable)new IOException("TYPE_PING length != 8: " + length); 
/* 283 */     if (streamId != 0) throw (Throwable)new IOException("TYPE_PING streamId != 0"); 
/* 284 */     int payload1 = this.source.readInt();
/* 285 */     int payload2 = this.source.readInt();
/* 286 */     boolean ack = ((flags & 0x1) != 0);
/* 287 */     handler.ping(ack, payload1, payload2);
/*     */   }
/*     */   
/*     */   private final void readGoAway(Handler handler, int length, int flags, int streamId) throws IOException
/*     */   {
/* 292 */     if (length < 8) throw (Throwable)new IOException("TYPE_GOAWAY length < 8: " + length); 
/* 293 */     if (streamId != 0) throw (Throwable)new IOException("TYPE_GOAWAY streamId != 0"); 
/* 294 */     int lastStreamId = this.source.readInt();
/* 295 */     int errorCodeInt = this.source.readInt();
/* 296 */     int opaqueDataLength = length - 8;
/* 297 */     if (ErrorCode.Companion.fromHttp2(errorCodeInt) != null) { ErrorCode errorCode = ErrorCode.Companion.fromHttp2(errorCodeInt);
/*     */       
/* 299 */       ByteString debugData = ByteString.EMPTY;
/* 300 */       if (opaqueDataLength > 0) {
/* 301 */         debugData = this.source.readByteString(opaqueDataLength);
/*     */       }
/* 303 */       handler.goAway(lastStreamId, errorCode, debugData);
/*     */       return; }
/*     */     
/*     */     ErrorCode.Companion.fromHttp2(errorCodeInt);
/*     */     throw (Throwable)new IOException("TYPE_GOAWAY unexpected error code: " + errorCodeInt); } private final void readWindowUpdate(Handler handler, int length, int flags, int streamId) throws IOException {
/* 308 */     if (length != 4) throw (Throwable)new IOException("TYPE_WINDOW_UPDATE length !=4: " + length); 
/* 309 */     long increment = Util.and(this.source.readInt(), 2147483647L);
/* 310 */     if (increment == 0L) throw (Throwable)new IOException("windowSizeIncrement was 0"); 
/* 311 */     handler.windowUpdate(streamId, increment);
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 316 */     this.source.close();
/*     */   }
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0004\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\021\n\002\020\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\000\b\000\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\b\020\027\032\0020\030H\026J\030\020\031\032\0020\0322\006\020\033\032\0020\0342\006\020\035\032\0020\032H\026J\b\020\036\032\0020\030H\002J\b\020\037\032\0020 H\026R\032\020\005\032\0020\006X\016¢\006\016\n\000\032\004\b\007\020\b\"\004\b\t\020\nR\032\020\013\032\0020\006X\016¢\006\016\n\000\032\004\b\f\020\b\"\004\b\r\020\nR\032\020\016\032\0020\006X\016¢\006\016\n\000\032\004\b\017\020\b\"\004\b\020\020\nR\032\020\021\032\0020\006X\016¢\006\016\n\000\032\004\b\022\020\b\"\004\b\023\020\nR\016\020\002\032\0020\003X\004¢\006\002\n\000R\032\020\024\032\0020\006X\016¢\006\016\n\000\032\004\b\025\020\b\"\004\b\026\020\n¨\006!"}, d2 = {"Lokhttp3/internal/http2/Http2Reader$ContinuationSource;", "Lokio/Source;", "source", "Lokio/BufferedSource;", "(Lokio/BufferedSource;)V", "flags", "", "getFlags", "()I", "setFlags", "(I)V", "left", "getLeft", "setLeft", "length", "getLength", "setLength", "padding", "getPadding", "setPadding", "streamId", "getStreamId", "setStreamId", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "readContinuationHeader", "timeout", "Lokio/Timeout;", "okhttp"})
/*     */   public static final class ContinuationSource implements Source { private int length; private int flags; private int streamId; private int left; private int padding;
/*     */     private final BufferedSource source;
/*     */     
/*     */     public ContinuationSource(@NotNull BufferedSource source) {
/* 323 */       this.source = source;
/*     */     }
/*     */     
/*     */     public final int getLength() {
/* 327 */       return this.length; } public final void setLength(int <set-?>) { this.length = <set-?>; }
/* 328 */     public final int getFlags() { return this.flags; } public final void setFlags(int <set-?>) { this.flags = <set-?>; }
/* 329 */     public final int getStreamId() { return this.streamId; } public final void setStreamId(int <set-?>) { this.streamId = <set-?>; }
/*     */     
/* 331 */     public final int getLeft() { return this.left; } public final void setLeft(int <set-?>) { this.left = <set-?>; }
/* 332 */     public final int getPadding() { return this.padding; } public final void setPadding(int <set-?>) { this.padding = <set-?>; }
/*     */ 
/*     */     
/*     */     public long read(@NotNull Buffer sink, long byteCount) throws IOException {
/* 336 */       Intrinsics.checkNotNullParameter(sink, "sink"); while (this.left == 0) {
/* 337 */         this.source.skip(this.padding);
/* 338 */         this.padding = 0;
/* 339 */         if ((this.flags & 0x4) != 0) return -1L; 
/* 340 */         readContinuationHeader();
/*     */       } 
/*     */ 
/*     */       
/* 344 */       long l1 = this.left; boolean bool = false; long read = this.source.read(sink, Math.min(byteCount, l1));
/* 345 */       if (read == -1L) return -1L; 
/* 346 */       this.left -= (int)read;
/* 347 */       return read;
/*     */     } @NotNull
/*     */     public Timeout timeout() {
/* 350 */       return this.source.timeout();
/*     */     }
/*     */ 
/*     */     
/*     */     public void close() throws IOException {}
/*     */ 
/*     */     
/*     */     private final void readContinuationHeader() throws IOException {
/* 358 */       int previousStreamId = this.streamId;
/*     */       
/* 360 */       this.left = Util.readMedium(this.source);
/* 361 */       this.length = this.left;
/* 362 */       int type = Util.and(this.source.readByte(), 255);
/* 363 */       this.flags = Util.and(this.source.readByte(), 255);
/* 364 */       if (Http2Reader.Companion.getLogger().isLoggable(Level.FINE)) Http2Reader.Companion.getLogger().fine(Http2.INSTANCE.frameLog(true, this.streamId, this.length, type, this.flags)); 
/* 365 */       this.streamId = this.source.readInt() & Integer.MAX_VALUE;
/* 366 */       if (type != 9) throw (Throwable)new IOException(type + " != TYPE_CONTINUATION"); 
/* 367 */       if (this.streamId != previousStreamId) throw (Throwable)new IOException("TYPE_CONTINUATION streamId changed");
/*     */     
/*     */     } }
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
/* 496 */   public static final Companion Companion = new Companion(null); static { Intrinsics.checkNotNullExpressionValue(Logger.getLogger(Http2.class.getName()), "Logger.getLogger(Http2::class.java.name)"); logger = Logger.getLogger(Http2.class.getName()); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000X\n\002\030\002\n\002\020\000\n\000\n\002\020\002\n\002\b\002\n\002\020\b\n\000\n\002\020\016\n\000\n\002\030\002\n\002\b\003\n\002\020\t\n\002\b\002\n\002\020\013\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\004\n\002\020 \n\002\030\002\n\002\b\016\n\002\030\002\n\002\b\003\bf\030\0002\0020\001J\b\020\002\032\0020\003H&J8\020\004\032\0020\0032\006\020\005\032\0020\0062\006\020\007\032\0020\b2\006\020\t\032\0020\n2\006\020\013\032\0020\b2\006\020\f\032\0020\0062\006\020\r\032\0020\016H&J(\020\017\032\0020\0032\006\020\020\032\0020\0212\006\020\005\032\0020\0062\006\020\022\032\0020\0232\006\020\024\032\0020\006H&J \020\025\032\0020\0032\006\020\026\032\0020\0062\006\020\027\032\0020\0302\006\020\031\032\0020\nH&J.\020\032\032\0020\0032\006\020\020\032\0020\0212\006\020\005\032\0020\0062\006\020\033\032\0020\0062\f\020\034\032\b\022\004\022\0020\0360\035H&J \020\037\032\0020\0032\006\020 \032\0020\0212\006\020!\032\0020\0062\006\020\"\032\0020\006H&J(\020#\032\0020\0032\006\020\005\032\0020\0062\006\020$\032\0020\0062\006\020%\032\0020\0062\006\020&\032\0020\021H&J&\020'\032\0020\0032\006\020\005\032\0020\0062\006\020(\032\0020\0062\f\020)\032\b\022\004\022\0020\0360\035H&J\030\020*\032\0020\0032\006\020\005\032\0020\0062\006\020\027\032\0020\030H&J\030\020+\032\0020\0032\006\020,\032\0020\0212\006\020+\032\0020-H&J\030\020.\032\0020\0032\006\020\005\032\0020\0062\006\020/\032\0020\016H&¨\0060"}, d2 = {"Lokhttp3/internal/http2/Http2Reader$Handler;", "", "ackSettings", "", "alternateService", "streamId", "", "origin", "", "protocol", "Lokio/ByteString;", "host", "port", "maxAge", "", "data", "inFinished", "", "source", "Lokio/BufferedSource;", "length", "goAway", "lastGoodStreamId", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "debugData", "headers", "associatedStreamId", "headerBlock", "", "Lokhttp3/internal/http2/Header;", "ping", "ack", "payload1", "payload2", "priority", "streamDependency", "weight", "exclusive", "pushPromise", "promisedStreamId", "requestHeaders", "rstStream", "settings", "clearPrevious", "Lokhttp3/internal/http2/Settings;", "windowUpdate", "windowSizeIncrement", "okhttp"}) public static interface Handler { void data(boolean param1Boolean, int param1Int1, @NotNull BufferedSource param1BufferedSource, int param1Int2) throws IOException; void headers(boolean param1Boolean, int param1Int1, int param1Int2, @NotNull List<Header> param1List); void rstStream(int param1Int, @NotNull ErrorCode param1ErrorCode); void settings(boolean param1Boolean, @NotNull Settings param1Settings); void ackSettings(); void ping(boolean param1Boolean, int param1Int1, int param1Int2); void goAway(int param1Int, @NotNull ErrorCode param1ErrorCode, @NotNull ByteString param1ByteString); void windowUpdate(int param1Int, long param1Long); void priority(int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean); void pushPromise(int param1Int1, int param1Int2, @NotNull List<Header> param1List) throws IOException; void alternateService(int param1Int1, @NotNull String param1String1, @NotNull ByteString param1ByteString, @NotNull String param1String2, int param1Int2, long param1Long); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\b\n\002\b\004\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\036\020\007\032\0020\b2\006\020\t\032\0020\b2\006\020\n\032\0020\b2\006\020\013\032\0020\bR\021\020\003\032\0020\004¢\006\b\n\000\032\004\b\005\020\006¨\006\f"}, d2 = {"Lokhttp3/internal/http2/Http2Reader$Companion;", "", "()V", "logger", "Ljava/util/logging/Logger;", "getLogger", "()Ljava/util/logging/Logger;", "lengthWithoutPadding", "", "length", "flags", "padding", "okhttp"}) public static final class Companion { @NotNull public final Logger getLogger() { return Http2Reader.logger; }
/*     */     
/*     */     private Companion() {}
/*     */     public final int lengthWithoutPadding(int length, int flags, int padding) throws IOException {
/* 500 */       int result = length;
/* 501 */       if ((flags & 0x8) != 0) result--; 
/* 502 */       if (padding > result) {
/* 503 */         throw (Throwable)new IOException("PROTOCOL_ERROR padding " + padding + " > remaining length " + result);
/*     */       }
/* 505 */       result -= padding;
/* 506 */       return result;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http2/Http2Reader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */