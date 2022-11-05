/*     */ package okhttp3.internal.http2;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import kotlin.Unit;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.Headers;
/*     */ import okhttp3.internal.Util;
/*     */ import okio.Buffer;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\001\n\002\030\002\n\002\020\000\n\000\n\002\020\b\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\006\n\002\020\t\n\002\b\t\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\013\n\002\020\002\n\002\b\f\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\b\n\002\020 \n\002\030\002\n\002\b\006\030\000 _2\0020\001:\004_`abB1\b\000\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007\022\006\020\b\032\0020\007\022\b\020\t\032\004\030\0010\n¢\006\002\020\013J\016\020@\032\0020A2\006\020B\032\0020#J\r\020C\032\0020AH\000¢\006\002\bDJ\r\020E\032\0020AH\000¢\006\002\bFJ\030\020G\032\0020A2\006\020H\032\0020\0172\b\020\024\032\004\030\0010\025J\032\020I\032\0020\0072\006\020\016\032\0020\0172\b\020\024\032\004\030\0010\025H\002J\016\020J\032\0020A2\006\020\016\032\0020\017J\016\020K\032\0020A2\006\020L\032\0020\nJ\006\020M\032\0020NJ\006\020O\032\0020PJ\006\020,\032\0020QJ\026\020R\032\0020A2\006\0204\032\0020S2\006\020T\032\0020\003J\026\020U\032\0020A2\006\020\t\032\0020\n2\006\020\b\032\0020\007J\016\020V\032\0020A2\006\020\016\032\0020\017J\006\020W\032\0020\nJ\006\020L\032\0020\nJ\r\020X\032\0020AH\000¢\006\002\bYJ$\020Z\032\0020A2\f\020[\032\b\022\004\022\0020]0\\2\006\020\006\032\0020\0072\006\020^\032\0020\007J\006\020>\032\0020QR\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\f\020\rR\036\020\016\032\004\030\0010\0178@X\016¢\006\016\n\000\032\004\b\020\020\021\"\004\b\022\020\023R\034\020\024\032\004\030\0010\025X\016¢\006\016\n\000\032\004\b\026\020\027\"\004\b\030\020\031R\016\020\032\032\0020\007X\016¢\006\002\n\000R\024\020\033\032\b\022\004\022\0020\n0\034X\004¢\006\002\n\000R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\035\020\036R\021\020\037\032\0020\0078F¢\006\006\032\004\b\037\020 R\021\020!\032\0020\0078F¢\006\006\032\004\b!\020 R$\020$\032\0020#2\006\020\"\032\0020#@@X\016¢\006\016\n\000\032\004\b%\020&\"\004\b'\020(R$\020)\032\0020#2\006\020\"\032\0020#@@X\016¢\006\016\n\000\032\004\b*\020&\"\004\b+\020(R\030\020,\032\0060-R\0020\000X\004¢\006\b\n\000\032\004\b.\020/R\030\0200\032\00601R\0020\000X\004¢\006\b\n\000\032\004\b2\0203R\030\0204\032\00605R\0020\000X\004¢\006\b\n\000\032\004\b6\0207R$\0208\032\0020#2\006\020\"\032\0020#@@X\016¢\006\016\n\000\032\004\b9\020&\"\004\b:\020(R$\020;\032\0020#2\006\020\"\032\0020#@@X\016¢\006\016\n\000\032\004\b<\020&\"\004\b=\020(R\030\020>\032\0060-R\0020\000X\004¢\006\b\n\000\032\004\b?\020/¨\006c"}, d2 = {"Lokhttp3/internal/http2/Http2Stream;", "", "id", "", "connection", "Lokhttp3/internal/http2/Http2Connection;", "outFinished", "", "inFinished", "headers", "Lokhttp3/Headers;", "(ILokhttp3/internal/http2/Http2Connection;ZZLokhttp3/Headers;)V", "getConnection", "()Lokhttp3/internal/http2/Http2Connection;", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "getErrorCode$okhttp", "()Lokhttp3/internal/http2/ErrorCode;", "setErrorCode$okhttp", "(Lokhttp3/internal/http2/ErrorCode;)V", "errorException", "Ljava/io/IOException;", "getErrorException$okhttp", "()Ljava/io/IOException;", "setErrorException$okhttp", "(Ljava/io/IOException;)V", "hasResponseHeaders", "headersQueue", "Ljava/util/ArrayDeque;", "getId", "()I", "isLocallyInitiated", "()Z", "isOpen", "<set-?>", "", "readBytesAcknowledged", "getReadBytesAcknowledged", "()J", "setReadBytesAcknowledged$okhttp", "(J)V", "readBytesTotal", "getReadBytesTotal", "setReadBytesTotal$okhttp", "readTimeout", "Lokhttp3/internal/http2/Http2Stream$StreamTimeout;", "getReadTimeout$okhttp", "()Lokhttp3/internal/http2/Http2Stream$StreamTimeout;", "sink", "Lokhttp3/internal/http2/Http2Stream$FramingSink;", "getSink$okhttp", "()Lokhttp3/internal/http2/Http2Stream$FramingSink;", "source", "Lokhttp3/internal/http2/Http2Stream$FramingSource;", "getSource$okhttp", "()Lokhttp3/internal/http2/Http2Stream$FramingSource;", "writeBytesMaximum", "getWriteBytesMaximum", "setWriteBytesMaximum$okhttp", "writeBytesTotal", "getWriteBytesTotal", "setWriteBytesTotal$okhttp", "writeTimeout", "getWriteTimeout$okhttp", "addBytesToWriteWindow", "", "delta", "cancelStreamIfNecessary", "cancelStreamIfNecessary$okhttp", "checkOutNotClosed", "checkOutNotClosed$okhttp", "close", "rstStatusCode", "closeInternal", "closeLater", "enqueueTrailers", "trailers", "getSink", "Lokio/Sink;", "getSource", "Lokio/Source;", "Lokio/Timeout;", "receiveData", "Lokio/BufferedSource;", "length", "receiveHeaders", "receiveRstStream", "takeHeaders", "waitForIo", "waitForIo$okhttp", "writeHeaders", "responseHeaders", "", "Lokhttp3/internal/http2/Header;", "flushHeaders", "Companion", "FramingSink", "FramingSource", "StreamTimeout", "okhttp"})
/*     */ public final class Http2Stream {
/*     */   private long readBytesTotal;
/*     */   private long readBytesAcknowledged;
/*     */   private long writeBytesTotal;
/*     */   private long writeBytesMaximum;
/*     */   private final ArrayDeque<Headers> headersQueue;
/*     */   private boolean hasResponseHeaders;
/*     */   @NotNull
/*     */   private final FramingSource source;
/*     */   @NotNull
/*     */   private final FramingSink sink;
/*     */   @NotNull
/*     */   private final StreamTimeout readTimeout;
/*     */   @NotNull
/*     */   private final StreamTimeout writeTimeout;
/*     */   @Nullable
/*     */   private ErrorCode errorCode;
/*     */   @Nullable
/*     */   private IOException errorException;
/*     */   private final int id;
/*     */   @NotNull
/*     */   private final Http2Connection connection;
/*     */   public static final long EMIT_BUFFER_SIZE = 16384L;
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*  38 */   public Http2Stream(int id, @NotNull Http2Connection connection, boolean outFinished, boolean inFinished, @Nullable Headers headers) { this.id = id; this.connection = connection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  61 */     this.writeBytesMaximum = this.connection.getPeerSettings().getInitialWindowSize();
/*     */ 
/*     */ 
/*     */     
/*  65 */     this.headersQueue = new ArrayDeque<>();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     this.source = new FramingSource(
/*  71 */         this.connection.getOkHttpSettings().getInitialWindowSize(), 
/*  72 */         inFinished);
/*     */     
/*  74 */     this.sink = new FramingSink(
/*  75 */         outFinished);
/*     */     
/*  77 */     this.readTimeout = new StreamTimeout();
/*  78 */     this.writeTimeout = new StreamTimeout();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     if (headers != null)
/*  94 */     { boolean bool1 = !isLocallyInitiated() ? true : false, bool2 = false, bool3 = false; if (!bool1)
/*     */       
/*     */       { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 693 */         int $i$a$-check-Http2Stream$1 = 0; String str = "locally-initiated streams shouldn't have headers yet"; throw (Throwable)new IllegalStateException(str.toString()); }  ArrayDeque<Headers> arrayDeque = this.headersQueue; bool2 = false; arrayDeque.add(headers); } else { boolean bool = isLocallyInitiated(); boolean bool1 = false, bool2 = false; if (!bool) { int $i$a$-check-Http2Stream$2 = 0; String str = "remotely-initiated streams should have headers"; throw (Throwable)new IllegalStateException(str.toString()); }  }  } public final int getId() { return this.id; } @NotNull public final Http2Connection getConnection() { return this.connection; } public final long getReadBytesTotal() { return this.readBytesTotal; } public final void setReadBytesTotal$okhttp(long <set-?>) { this.readBytesTotal = <set-?>; } public final long getReadBytesAcknowledged() { return this.readBytesAcknowledged; } public final void setReadBytesAcknowledged$okhttp(long <set-?>) { this.readBytesAcknowledged = <set-?>; } public final long getWriteBytesTotal() { return this.writeBytesTotal; } public final void setWriteBytesTotal$okhttp(long <set-?>) { this.writeBytesTotal = <set-?>; } public final long getWriteBytesMaximum() { return this.writeBytesMaximum; } public final void setWriteBytesMaximum$okhttp(long <set-?>) { this.writeBytesMaximum = <set-?>; } @NotNull public final FramingSource getSource$okhttp() { return this.source; } @NotNull public final FramingSink getSink$okhttp() { return this.sink; } @NotNull public final StreamTimeout getReadTimeout$okhttp() { return this.readTimeout; } @NotNull public final StreamTimeout getWriteTimeout$okhttp() { return this.writeTimeout; } @Nullable public final synchronized ErrorCode getErrorCode$okhttp() { return this.errorCode; } public final void setErrorCode$okhttp(@Nullable ErrorCode <set-?>) { this.errorCode = <set-?>; } @Nullable public final IOException getErrorException$okhttp() { return this.errorException; } public final void setErrorException$okhttp(@Nullable IOException <set-?>) { this.errorException = <set-?>; } public final synchronized boolean isOpen() { if (this.errorCode != null) return false;  if ((this.source.getFinished$okhttp() || this.source.getClosed$okhttp()) && (this.sink.getFinished() || this.sink.getClosed()) && this.hasResponseHeaders) return false;  return true; } public final boolean isLocallyInitiated() { boolean streamIsClient = ((this.id & 0x1) == 1); return (this.connection.getClient$okhttp() == streamIsClient); } @NotNull public final synchronized Headers takeHeaders() throws IOException { this.readTimeout.enter(); try { while (this.headersQueue.isEmpty() && this.errorCode == null) waitForIo$okhttp();  } finally { this.readTimeout.exitAndThrowIfTimedOut(); }  ArrayDeque<Headers> arrayDeque = this.headersQueue; boolean bool = false; if (!arrayDeque.isEmpty()) { Intrinsics.checkNotNullExpressionValue(this.headersQueue.removeFirst(), "headersQueue.removeFirst()"); return this.headersQueue.removeFirst(); }  Intrinsics.checkNotNull(this.errorCode); throw (this.errorException != null) ? (Throwable)this.errorException : (Throwable)new StreamResetException(this.errorCode); } @NotNull public final synchronized Headers trailers() throws IOException { if (this.source.getFinished$okhttp() && this.source.getReceiveBuffer().exhausted() && this.source.getReadBuffer().exhausted()) { if (this.source.getTrailers() == null) this.source.getTrailers();  return Util.EMPTY_HEADERS; }  if (this.errorCode != null) { Intrinsics.checkNotNull(this.errorCode); throw (this.errorException != null) ? (Throwable)this.errorException : (Throwable)new StreamResetException(this.errorCode); }  throw (Throwable)new IllegalStateException("too early; can't read the trailers yet"); } public final void writeHeaders(@NotNull List<Header> responseHeaders, boolean outFinished, boolean flushHeaders) throws IOException { Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders"); Object $this$assertThreadDoesntHoldLock$iv = this; int $i$f$assertThreadDoesntHoldLock = 0; if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) { Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); }  boolean bool = flushHeaders; Http2Stream http2Stream = this; boolean bool1 = false; synchronized (false) { int $i$a$-synchronized-Http2Stream$writeHeaders$1 = 0; this.hasResponseHeaders = true; if (outFinished) this.sink.setFinished(true);  Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Stream}, name=null} */ }  if (!bool) { Http2Connection http2Connection = this.connection; bool1 = false; synchronized (false) { int $i$a$-synchronized-Http2Stream$writeHeaders$2 = 0; bool = (this.connection.getWriteBytesTotal() >= this.connection.getWriteBytesMaximum()); Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */ }  }  this.connection.writeHeaders$okhttp(this.id, outFinished, responseHeaders); if (bool) this.connection.flush();  } public final void enqueueTrailers(@NotNull Headers trailers) { Intrinsics.checkNotNullParameter(trailers, "trailers"); Http2Stream http2Stream = this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-Http2Stream$enqueueTrailers$1 = 0; boolean bool1 = !this.sink.getFinished() ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-Http2Stream$enqueueTrailers$1$1 = 0; String str = "already finished"; throw (Throwable)new IllegalStateException(str.toString()); }  bool1 = (trailers.size() != 0) ? true : false; bool2 = false; bool3 = false; if (!bool1) { int $i$a$-require-Http2Stream$enqueueTrailers$1$2 = 0; String str = "trailers.size() == 0"; throw (Throwable)new IllegalArgumentException(str.toString()); }  this.sink.setTrailers(trailers); Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Stream}, name=null} */ }  } @NotNull public final Timeout readTimeout() { return (Timeout)this.readTimeout; } @NotNull public final Timeout writeTimeout() { return (Timeout)this.writeTimeout; } @NotNull public final Source getSource() { return this.source; } @NotNull public final Sink getSink() { Http2Stream http2Stream = this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-Http2Stream$getSink$1 = 0; boolean bool1 = (this.hasResponseHeaders || isLocallyInitiated()) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-Http2Stream$getSink$1$1 = 0; String str = "reply before requesting the sink"; throw (Throwable)new IllegalStateException(str.toString()); }  Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Stream}, name=null} */ }  return this.sink; } public final void close(@NotNull ErrorCode rstStatusCode, @Nullable IOException errorException) throws IOException { Intrinsics.checkNotNullParameter(rstStatusCode, "rstStatusCode"); if (!closeInternal(rstStatusCode, errorException)) return;  this.connection.writeSynReset$okhttp(this.id, rstStatusCode); } public final void closeLater(@NotNull ErrorCode errorCode) { Intrinsics.checkNotNullParameter(errorCode, "errorCode"); if (!closeInternal(errorCode, null)) return;  this.connection.writeSynResetLater$okhttp(this.id, errorCode); }
/* 694 */   private final boolean closeInternal(ErrorCode errorCode, IOException errorException) { Object $this$assertThreadDoesntHoldLock$iv = this; int $i$f$assertThreadDoesntHoldLock = 0; if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) {
/* 695 */       Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv);
/*     */     }  $this$assertThreadDoesntHoldLock$iv = this; $i$f$assertThreadDoesntHoldLock = 0; synchronized (false) { int $i$a$-synchronized-Http2Stream$closeInternal$1 = 0; if (this.errorCode != null) { boolean bool = false; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=$this$assertThreadDoesntHoldLock$iv} */ return bool; }
/*     */        if (this.source.getFinished$okhttp() && this.sink.getFinished()) { boolean bool = false; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=$this$assertThreadDoesntHoldLock$iv} */ return bool; }
/* 698 */        this.errorCode = errorCode; this.errorException = errorException; Object $this$notifyAll$iv = this; int $i$f$notifyAll = 0; if ($this$notifyAll$iv == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");  $this$notifyAll$iv.notifyAll(); Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=$this$assertThreadDoesntHoldLock$iv} */ }
/* 699 */      this.connection.removeStream$okhttp(this.id); return true; } public final void receiveData(@NotNull BufferedSource source, int length) throws IOException { Intrinsics.checkNotNullParameter(source, "source"); Object $this$assertThreadDoesntHoldLock$iv = this; int $i$f$assertThreadDoesntHoldLock = 0; if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) {
/* 700 */       Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv);
/*     */     }  this.source.receive$okhttp(source, length); } public final void receiveHeaders(@NotNull Headers headers, boolean inFinished) { Intrinsics.checkNotNullParameter(headers, "headers"); Object $this$assertThreadDoesntHoldLock$iv = this;
/*     */     int $i$f$assertThreadDoesntHoldLock = 0;
/* 703 */     if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
/* 704 */     { Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); }  boolean open = false; Http2Stream http2Stream = this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-Http2Stream$receiveHeaders$1 = 0; if (!this.hasResponseHeaders || !inFinished) { this.hasResponseHeaders = true; ArrayDeque<Headers> arrayDeque = this.headersQueue; Headers headers1 = headers; boolean bool1 = false; arrayDeque.add(headers1); }
/*     */       else { this.source.setTrailers(headers); }
/*     */        if (inFinished)
/* 707 */         this.source.setFinished$okhttp(true);  open = isOpen(); Object $this$notifyAll$iv = this; int $i$f$notifyAll = 0; if ($this$notifyAll$iv == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");  $this$notifyAll$iv.notifyAll(); Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Stream}, name=null} */ }  if (!open) this.connection.removeStream$okhttp(this.id);  } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000@\n\002\030\002\n\002\030\002\n\000\n\002\020\t\n\000\n\002\020\013\n\002\b\t\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\020\002\n\002\b\005\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\b\004\030\0002\0020\001B\027\b\000\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005¢\006\002\020\006J\b\020\032\032\0020\033H\026J\030\020\034\032\0020\0032\006\020\035\032\0020\0172\006\020\036\032\0020\003H\026J\035\020\037\032\0020\0332\006\020 \032\0020!2\006\020\036\032\0020\003H\000¢\006\002\b\"J\b\020#\032\0020$H\026J\020\020%\032\0020\0332\006\020\034\032\0020\003H\002R\032\020\007\032\0020\005X\016¢\006\016\n\000\032\004\b\b\020\t\"\004\b\n\020\013R\032\020\004\032\0020\005X\016¢\006\016\n\000\032\004\b\f\020\t\"\004\b\r\020\013R\016\020\002\032\0020\003X\004¢\006\002\n\000R\021\020\016\032\0020\017¢\006\b\n\000\032\004\b\020\020\021R\021\020\022\032\0020\017¢\006\b\n\000\032\004\b\023\020\021R\034\020\024\032\004\030\0010\025X\016¢\006\016\n\000\032\004\b\026\020\027\"\004\b\030\020\031¨\006&"}, d2 = {"Lokhttp3/internal/http2/Http2Stream$FramingSource;", "Lokio/Source;", "maxByteCount", "", "finished", "", "(Lokhttp3/internal/http2/Http2Stream;JZ)V", "closed", "getClosed$okhttp", "()Z", "setClosed$okhttp", "(Z)V", "getFinished$okhttp", "setFinished$okhttp", "readBuffer", "Lokio/Buffer;", "getReadBuffer", "()Lokio/Buffer;", "receiveBuffer", "getReceiveBuffer", "trailers", "Lokhttp3/Headers;", "getTrailers", "()Lokhttp3/Headers;", "setTrailers", "(Lokhttp3/Headers;)V", "close", "", "read", "sink", "byteCount", "receive", "source", "Lokio/BufferedSource;", "receive$okhttp", "timeout", "Lokio/Timeout;", "updateConnectionFlowControl", "okhttp"}) public final class FramingSource implements Source {
/* 708 */     @NotNull private final Buffer receiveBuffer; @NotNull private final Buffer readBuffer; @Nullable private Headers trailers; private boolean closed; private final long maxByteCount; private boolean finished; public FramingSource(long maxByteCount, boolean finished) { this.maxByteCount = maxByteCount; this.finished = finished; this.receiveBuffer = new Buffer(); this.readBuffer = new Buffer(); } public final boolean getFinished$okhttp() { return this.finished; } public final void setFinished$okhttp(boolean <set-?>) { this.finished = <set-?>; } @NotNull public final Buffer getReceiveBuffer() { return this.receiveBuffer; } @NotNull public final Buffer getReadBuffer() { return this.readBuffer; } @Nullable public final Headers getTrailers() { return this.trailers; } public final void setTrailers(@Nullable Headers <set-?>) { this.trailers = <set-?>; } public final boolean getClosed$okhttp() { return this.closed; } public final void setClosed$okhttp(boolean <set-?>) { this.closed = <set-?>; } public long read(@NotNull Buffer sink, long byteCount) throws IOException { Object errorExceptionToDeliver; long readBytesDelivered; Intrinsics.checkNotNullParameter(sink, "sink"); boolean bool1 = (byteCount >= 0L) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-require-Http2Stream$FramingSource$read$1 = 0; errorExceptionToDeliver = "byteCount < 0: " + byteCount; throw (Throwable)new IllegalArgumentException(errorExceptionToDeliver.toString()); }  while (true) { boolean tryAgain = false; readBytesDelivered = -1L; errorExceptionToDeliver = null; Http2Stream http2Stream = Http2Stream.this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-Http2Stream$FramingSource$read$2 = 0; Http2Stream.this.getReadTimeout$okhttp().enter(); try { if (Http2Stream.this.getErrorCode$okhttp() != null) { if (Http2Stream.this.getErrorException$okhttp() == null) { Http2Stream.this.getErrorException$okhttp(); Intrinsics.checkNotNull(Http2Stream.this.getErrorCode$okhttp()); }  errorExceptionToDeliver = new StreamResetException(Http2Stream.this.getErrorCode$okhttp()); }  if (this.closed) throw (Throwable)new IOException("stream closed");  if (this.readBuffer.size() > 0L) { long l1 = byteCount, l2 = this.readBuffer.size(); boolean bool4 = false; readBytesDelivered = this.readBuffer.read(sink, Math.min(l1, l2)); Http2Stream.this.setReadBytesTotal$okhttp(Http2Stream.this.getReadBytesTotal() + readBytesDelivered); long unacknowledgedBytesRead = Http2Stream.this.getReadBytesTotal() - Http2Stream.this.getReadBytesAcknowledged(); if (errorExceptionToDeliver == null && unacknowledgedBytesRead >= (Http2Stream.this.getConnection().getOkHttpSettings().getInitialWindowSize() / 2)) { Http2Stream.this.getConnection().writeWindowUpdateLater$okhttp(Http2Stream.this.getId(), unacknowledgedBytesRead); Http2Stream.this.setReadBytesAcknowledged$okhttp(Http2Stream.this.getReadBytesTotal()); }  } else if (!this.finished && errorExceptionToDeliver == null) { Http2Stream.this.waitForIo$okhttp(); tryAgain = true; }  } finally { Http2Stream.this.getReadTimeout$okhttp().exitAndThrowIfTimedOut(); }  Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Stream}, name=null} */ }  if (tryAgain) continue;  break; }  if (readBytesDelivered != -1L) { updateConnectionFlowControl(readBytesDelivered); return readBytesDelivered; }  if (errorExceptionToDeliver != null) { Intrinsics.checkNotNull(errorExceptionToDeliver); throw (Throwable)errorExceptionToDeliver; }  return -1L; } private final void updateConnectionFlowControl(long read) { Object $this$assertThreadDoesntHoldLock$iv = Http2Stream.this; int $i$f$assertThreadDoesntHoldLock = 0; if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) { Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); }  Http2Stream.this.getConnection().updateConnectionFlowControl$okhttp(read); } public final void receive$okhttp(@NotNull BufferedSource source, long byteCount) throws IOException { Intrinsics.checkNotNullParameter(source, "source"); Object $this$assertThreadDoesntHoldLock$iv = Http2Stream.this; int $i$f$assertThreadDoesntHoldLock = 0; if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) { Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); }  long l = byteCount; while (l > 0L) { boolean finished = false; boolean flowControlError = false; Http2Stream http2Stream1 = Http2Stream.this; boolean bool1 = false; synchronized (false) { int $i$a$-synchronized-Http2Stream$FramingSource$receive$1 = 0; finished = this.finished; flowControlError = (l + this.readBuffer.size() > this.maxByteCount); Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Stream}, name=null} */ }  if (flowControlError) { source.skip(l); Http2Stream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR); return; }  if (finished) { source.skip(l); return; }  long read = source.read(this.receiveBuffer, l); if (read == -1L) throw (Throwable)new EOFException();  l -= read; long bytesDiscarded = 0L; Http2Stream http2Stream2 = Http2Stream.this; boolean bool2 = false; synchronized (false) { int $i$a$-synchronized-Http2Stream$FramingSource$receive$2 = 0; if (this.closed) { bytesDiscarded = this.receiveBuffer.size(); this.receiveBuffer.clear(); } else { boolean wasEmpty = (this.readBuffer.size() == 0L); this.readBuffer.writeAll((Source)this.receiveBuffer); if (wasEmpty) { Object $this$notifyAll$iv = Http2Stream.this; int $i$f$notifyAll = 0; if ($this$notifyAll$iv == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");  $this$notifyAll$iv.notifyAll(); }  }  Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Stream}, name=null} */ }  if (bytesDiscarded > 0L) updateConnectionFlowControl(bytesDiscarded);  }  } @NotNull public Timeout timeout() { return (Timeout)Http2Stream.this.getReadTimeout$okhttp(); } public void close() throws IOException { long bytesDiscarded = 0L; Http2Stream http2Stream = Http2Stream.this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-Http2Stream$FramingSource$close$1 = 0; this.closed = true; bytesDiscarded = this.readBuffer.size(); this.readBuffer.clear(); Object $this$notifyAll$iv = Http2Stream.this; int $i$f$notifyAll = 0; if ($this$notifyAll$iv == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");  $this$notifyAll$iv.notifyAll(); Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Stream}, name=null} */ }  if (bytesDiscarded > 0L) updateConnectionFlowControl(bytesDiscarded);  Http2Stream.this.cancelStreamIfNecessary$okhttp(); } } public final synchronized void receiveRstStream(@NotNull ErrorCode errorCode) { Intrinsics.checkNotNullParameter(errorCode, "errorCode"); if (this.errorCode == null) { this.errorCode = errorCode; Object $this$notifyAll$iv = this; int $i$f$notifyAll = 0; if ($this$notifyAll$iv == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");  $this$notifyAll$iv.notifyAll(); }
/* 709 */      } public final void cancelStreamIfNecessary$okhttp() throws IOException { Object $this$assertThreadDoesntHoldLock$iv = this; int $i$f$assertThreadDoesntHoldLock = 0; if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
/* 710 */     { Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); }  boolean open = false; boolean cancel = false; Http2Stream http2Stream = this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-Http2Stream$cancelStreamIfNecessary$1 = 0; cancel = (!this.source.getFinished$okhttp() && this.source.getClosed$okhttp() && (this.sink.getFinished() || this.sink.getClosed())); open = isOpen(); Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Stream}, name=null} */ }  if (cancel) { close(ErrorCode.CANCEL, null); } else if (!open) { this.connection.removeStream$okhttp(this.id); }  } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0006\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\002\b\t\n\002\030\002\n\000\n\002\030\002\n\002\b\005\n\002\020\002\n\002\b\004\n\002\030\002\n\002\b\003\n\002\020\t\n\000\b\004\030\0002\0020\001B\017\022\b\b\002\020\002\032\0020\003¢\006\002\020\004J\b\020\024\032\0020\025H\026J\020\020\026\032\0020\0252\006\020\027\032\0020\003H\002J\b\020\030\032\0020\025H\026J\b\020\031\032\0020\032H\026J\030\020\033\032\0020\0252\006\020\034\032\0020\r2\006\020\035\032\0020\036H\026R\032\020\005\032\0020\003X\016¢\006\016\n\000\032\004\b\006\020\007\"\004\b\b\020\tR\032\020\002\032\0020\003X\016¢\006\016\n\000\032\004\b\n\020\007\"\004\b\013\020\tR\016\020\f\032\0020\rX\004¢\006\002\n\000R\034\020\016\032\004\030\0010\017X\016¢\006\016\n\000\032\004\b\020\020\021\"\004\b\022\020\023¨\006\037"}, d2 = {"Lokhttp3/internal/http2/Http2Stream$FramingSink;", "Lokio/Sink;", "finished", "", "(Lokhttp3/internal/http2/Http2Stream;Z)V", "closed", "getClosed", "()Z", "setClosed", "(Z)V", "getFinished", "setFinished", "sendBuffer", "Lokio/Buffer;", "trailers", "Lokhttp3/Headers;", "getTrailers", "()Lokhttp3/Headers;", "setTrailers", "(Lokhttp3/Headers;)V", "close", "", "emitFrame", "outFinishedOnLastFrame", "flush", "timeout", "Lokio/Timeout;", "write", "source", "byteCount", "", "okhttp"}) public final class FramingSink implements Sink {
/*     */     private final Buffer sendBuffer; @Nullable private Headers trailers; private boolean closed; private boolean finished; public FramingSink(boolean finished) { this.finished = finished; this.sendBuffer = new Buffer(); } public final boolean getFinished() { return this.finished; } public final void setFinished(boolean <set-?>) { this.finished = <set-?>; } @Nullable public final Headers getTrailers() { return this.trailers; } public final void setTrailers(@Nullable Headers <set-?>) { this.trailers = <set-?>; } public final boolean getClosed() { return this.closed; } public final void setClosed(boolean <set-?>) { this.closed = <set-?>; } public void write(@NotNull Buffer source, long byteCount) throws IOException { Intrinsics.checkNotNullParameter(source, "source"); Object $this$assertThreadDoesntHoldLock$iv = Http2Stream.this; int $i$f$assertThreadDoesntHoldLock = 0; if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) { Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); }  this.sendBuffer.write(source, byteCount); while (this.sendBuffer.size() >= 16384L) emitFrame(false);  } private final void emitFrame(boolean outFinishedOnLastFrame) throws IOException { long toWrite = 0L; boolean outFinished = false; null = Http2Stream.this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-Http2Stream$FramingSink$emitFrame$1 = 0; Http2Stream.this.getWriteTimeout$okhttp().enter(); try { while (Http2Stream.this.getWriteBytesTotal() >= Http2Stream.this.getWriteBytesMaximum() && !this.finished && !this.closed && Http2Stream.this.getErrorCode$okhttp() == null) Http2Stream.this.waitForIo$okhttp();  } finally { Http2Stream.this.getWriteTimeout$okhttp().exitAndThrowIfTimedOut(); }  Http2Stream.this.checkOutNotClosed$okhttp(); long l1 = Http2Stream.this.getWriteBytesMaximum() - Http2Stream.this.getWriteBytesTotal(), l2 = this.sendBuffer.size(); boolean bool1 = false; toWrite = Math.min(l1, l2); Http2Stream.this.setWriteBytesTotal$okhttp(Http2Stream.this.getWriteBytesTotal() + toWrite); outFinished = (outFinishedOnLastFrame && toWrite == this.sendBuffer.size()); Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Stream}, name=null} */ }  Http2Stream.this.getWriteTimeout$okhttp().enter(); try { Http2Stream.this.getConnection().writeData(Http2Stream.this.getId(), outFinished, this.sendBuffer, toWrite); } finally { Http2Stream.this.getWriteTimeout$okhttp().exitAndThrowIfTimedOut(); }  } public void flush() throws IOException { Object $this$assertThreadDoesntHoldLock$iv = Http2Stream.this; int $i$f$assertThreadDoesntHoldLock = 0; if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) { Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); }  $this$assertThreadDoesntHoldLock$iv = Http2Stream.this; $i$f$assertThreadDoesntHoldLock = 0; synchronized (false) { int $i$a$-synchronized-Http2Stream$FramingSink$flush$1 = 0; Http2Stream.this.checkOutNotClosed$okhttp(); Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=$this$assertThreadDoesntHoldLock$iv} */ }  while (this.sendBuffer.size() > 0L) { emitFrame(false); Http2Stream.this.getConnection().flush(); }  } @NotNull public Timeout timeout() { return (Timeout)Http2Stream.this.getWriteTimeout$okhttp(); } public void close() throws IOException { Object $this$assertThreadDoesntHoldLock$iv = Http2Stream.this; int $i$f$assertThreadDoesntHoldLock = 0; if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) { Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); }  boolean outFinished = false; Http2Stream http2Stream = Http2Stream.this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-Http2Stream$FramingSink$close$1 = 0; if (this.closed) { /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Stream}, name=null} */ return; }  outFinished = (Http2Stream.this.getErrorCode$okhttp() == null); Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Stream}, name=null} */ }  if (!(Http2Stream.this.getSink$okhttp()).finished) { boolean hasData = (this.sendBuffer.size() > 0L); boolean hasTrailers = (this.trailers != null); if (hasTrailers) { while (this.sendBuffer.size() > 0L) emitFrame(false);  Intrinsics.checkNotNull(this.trailers); Http2Stream.this.getConnection().writeHeaders$okhttp(Http2Stream.this.getId(), outFinished, Util.toHeaderList(this.trailers)); } else if (hasData) { while (this.sendBuffer.size() > 0L) emitFrame(true);  } else if (outFinished) { Http2Stream.this.getConnection().writeData(Http2Stream.this.getId(), true, null, 0L); }  }  http2Stream = Http2Stream.this; bool = false; synchronized (false) { int $i$a$-synchronized-Http2Stream$FramingSink$close$2 = 0; this.closed = true; Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Stream}, name=null} */ }  Http2Stream.this.getConnection().flush(); Http2Stream.this.cancelStreamIfNecessary$okhttp(); } } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\022\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\t\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\016\020\003\032\0020\004XT¢\006\002\n\000¨\006\005"}, d2 = {"Lokhttp3/internal/http2/Http2Stream$Companion;", "", "()V", "EMIT_BUFFER_SIZE", "", "okhttp"}) public static final class Companion {
/*     */     private Companion() {} }
/* 713 */   public final void addBytesToWriteWindow(long delta) { this.writeBytesMaximum += delta; if (delta > 0L) { Object $this$notifyAll$iv = this; int $i$f$notifyAll = 0; if ($this$notifyAll$iv == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");  $this$notifyAll$iv.notifyAll(); }  } public final void checkOutNotClosed$okhttp() throws IOException { if (this.sink.getClosed()) throw (Throwable)new IOException("stream closed");  if (this.sink.getFinished()) throw (Throwable)new IOException("stream finished");  if (this.errorCode != null) { Intrinsics.checkNotNull(this.errorCode); throw (this.errorException != null) ? (Throwable)this.errorException : (Throwable)new StreamResetException(this.errorCode); }  }
/* 714 */   public final void waitForIo$okhttp() throws InterruptedIOException { try { Object $this$wait$iv = this; int $i$f$wait = 0; if ($this$wait$iv == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");  $this$wait$iv.wait(); }
/*     */     catch (InterruptedException _)
/*     */     { Thread.currentThread().interrupt();
/*     */       throw (Throwable)new InterruptedIOException(); }
/*     */      }
/*     */ 
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\032\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\002\b\003\b\004\030\0002\0020\001B\005¢\006\002\020\002J\006\020\003\032\0020\004J\022\020\005\032\0020\0062\b\020\007\032\004\030\0010\006H\024J\b\020\b\032\0020\004H\024¨\006\t"}, d2 = {"Lokhttp3/internal/http2/Http2Stream$StreamTimeout;", "Lokio/AsyncTimeout;", "(Lokhttp3/internal/http2/Http2Stream;)V", "exitAndThrowIfTimedOut", "", "newTimeoutException", "Ljava/io/IOException;", "cause", "timedOut", "okhttp"})
/*     */   public final class StreamTimeout extends AsyncTimeout {
/*     */     protected void timedOut() {
/*     */       Http2Stream.this.closeLater(ErrorCode.CANCEL);
/*     */       Http2Stream.this.getConnection().sendDegradedPingLater$okhttp();
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     protected IOException newTimeoutException(@Nullable IOException cause) {
/*     */       SocketTimeoutException socketTimeoutException1 = new SocketTimeoutException("timeout");
/*     */       boolean bool1 = false, bool2 = false;
/*     */       SocketTimeoutException $this$apply = socketTimeoutException1;
/*     */       int $i$a$-apply-Http2Stream$StreamTimeout$newTimeoutException$1 = 0;
/*     */       if (cause != null)
/*     */         $this$apply.initCause(cause); 
/*     */       return socketTimeoutException1;
/*     */     }
/*     */     
/*     */     public final void exitAndThrowIfTimedOut() throws IOException {
/*     */       if (exit())
/*     */         throw (Throwable)newTimeoutException(null); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http2/Http2Stream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */