/*     */ package okhttp3;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.net.ProtocolException;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.JvmName;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.internal.http1.HeadersReader;
/*     */ import okio.Buffer;
/*     */ import okio.BufferedSource;
/*     */ import okio.ByteString;
/*     */ import okio.Okio;
/*     */ import okio.Options;
/*     */ import okio.Source;
/*     */ import okio.Timeout;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000P\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\002\b\003\n\002\020\013\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\b\n\000\n\002\020\002\n\000\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\004\030\000 \0342\0020\001:\003\034\035\036B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B\025\022\006\020\005\032\0020\006\022\006\020\007\032\0020\b¢\006\002\020\tJ\b\020\025\032\0020\026H\026J\020\020\027\032\0020\0302\006\020\031\032\0020\030H\002J\b\020\032\032\004\030\0010\033R\023\020\007\032\0020\b8\007¢\006\b\n\000\032\004\b\007\020\nR\016\020\013\032\0020\fX\016¢\006\002\n\000R\016\020\r\032\0020\016X\004¢\006\002\n\000R\024\020\017\032\b\030\0010\020R\0020\000X\016¢\006\002\n\000R\016\020\021\032\0020\016X\004¢\006\002\n\000R\016\020\022\032\0020\fX\016¢\006\002\n\000R\016\020\023\032\0020\024X\016¢\006\002\n\000R\016\020\005\032\0020\006X\004¢\006\002\n\000¨\006\037"}, d2 = {"Lokhttp3/MultipartReader;", "Ljava/io/Closeable;", "response", "Lokhttp3/ResponseBody;", "(Lokhttp3/ResponseBody;)V", "source", "Lokio/BufferedSource;", "boundary", "", "(Lokio/BufferedSource;Ljava/lang/String;)V", "()Ljava/lang/String;", "closed", "", "crlfDashDashBoundary", "Lokio/ByteString;", "currentPart", "Lokhttp3/MultipartReader$PartSource;", "dashDashBoundary", "noMoreParts", "partCount", "", "close", "", "currentPartBytesRemaining", "", "maxResult", "nextPart", "Lokhttp3/MultipartReader$Part;", "Companion", "Part", "PartSource", "okhttp"})
/*     */ public final class MultipartReader
/*     */   implements Closeable
/*     */ {
/*     */   private final ByteString dashDashBoundary;
/*     */   private final ByteString crlfDashDashBoundary;
/*     */   private int partCount;
/*     */   private boolean closed;
/*     */   private boolean noMoreParts;
/*     */   private PartSource currentPart;
/*     */   private final BufferedSource source;
/*     */   @NotNull
/*     */   private final String boundary;
/*     */   @NotNull
/*     */   private static final Options afterBoundaryOptions;
/*     */   
/*     */   public MultipartReader(@NotNull BufferedSource source, @NotNull String boundary) throws IOException {
/*  57 */     this.source = source; this.boundary = boundary;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  65 */     this.dashDashBoundary = (new Buffer()).writeUtf8("--").writeUtf8(this.boundary).readByteString();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  74 */     this.crlfDashDashBoundary = (new Buffer()).writeUtf8("\r\n--").writeUtf8(this.boundary).readByteString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @JvmName(name = "boundary")
/*     */   @NotNull
/*     */   public final String boundary() {
/*     */     return this.boundary;
/*     */   }
/*     */ 
/*     */   
/*     */   public MultipartReader(@NotNull ResponseBody response) throws IOException {}
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public final Part nextPart() throws IOException
/*     */   {
/*  92 */     boolean bool1 = !this.closed ? true : false; int i = 0; boolean bool2 = false; if (!bool1)
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 211 */       int $i$a$-check-MultipartReader$nextPart$1 = 0; String str = "closed"; throw (Throwable)new IllegalStateException(str.toString()); }  if (this.noMoreParts) return null;  if (this.partCount == 0 && this.source.rangeEquals(0L, this.dashDashBoundary)) { this.source.skip(this.dashDashBoundary.size()); } else { while (true) { long toSkip = currentPartBytesRemaining(8192L); if (toSkip == 0L) break;  this.source.skip(toSkip); }  this.source.skip(this.crlfDashDashBoundary.size()); }  boolean whitespace = false; while (true) { switch (this.source.select(afterBoundaryOptions)) { case 0: this.partCount = (i = this.partCount) + 1; break;case 1: if (whitespace) throw (Throwable)new ProtocolException("unexpected characters after boundary");  if (this.partCount == 0) throw (Throwable)new ProtocolException("expected at least 1 part");  this.noMoreParts = true; return null;case 2: case 3: whitespace = true;case -1: throw (Throwable)new ProtocolException("unexpected characters after boundary"); }  }  Headers headers = (new HeadersReader(this.source)).readHeaders(); PartSource partSource = new PartSource(); this.currentPart = partSource; return new Part(headers, Okio.buffer(partSource)); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000&\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\002\b\004\030\0002\0020\001B\005¢\006\002\020\002J\b\020\005\032\0020\006H\026J\030\020\007\032\0020\b2\006\020\t\032\0020\n2\006\020\013\032\0020\bH\026J\b\020\003\032\0020\004H\026R\016\020\003\032\0020\004X\004¢\006\002\n\000¨\006\f"}, d2 = {"Lokhttp3/MultipartReader$PartSource;", "Lokio/Source;", "(Lokhttp3/MultipartReader;)V", "timeout", "Lokio/Timeout;", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "okhttp"}) private final class PartSource implements Source { public long read(@NotNull Buffer sink, long byteCount) { Exception exception; Intrinsics.checkNotNullParameter(sink, "sink"); boolean bool = (byteCount >= 0L); boolean bool1 = false, bool2 = false; if (!bool) { int $i$a$-require-MultipartReader$PartSource$read$1 = 0; String str = "byteCount < 0: " + byteCount; throw (Throwable)new IllegalArgumentException(str.toString()); }  bool = Intrinsics.areEqual(MultipartReader.this.currentPart, this); bool1 = false; bool2 = false; if (!bool) { int $i$a$-check-MultipartReader$PartSource$read$2 = 0; String str = "closed"; throw (Throwable)new IllegalStateException(str.toString()); }
/* 212 */        Timeout timeout1 = MultipartReader.this.source.timeout(), other$iv = this.timeout; int $i$f$intersectWith = 0; long originalTimeout$iv = timeout1.timeoutNanos();
/* 213 */       timeout1.timeout(Timeout.Companion.minTimeout(other$iv.timeoutNanos(), timeout1.timeoutNanos()), TimeUnit.NANOSECONDS);
/*     */       
/* 215 */       if (timeout1.hasDeadline()) {
/* 216 */         Exception exception1; long originalDeadline$iv = timeout1.deadlineNanoTime();
/* 217 */         if (other$iv.hasDeadline()) {
/* 218 */           timeout1.deadlineNanoTime(Math.min(timeout1.deadlineNanoTime(), other$iv.deadlineNanoTime()));
/*     */         }
/*     */         
/* 221 */         try { int $i$a$-intersectWith-MultipartReader$PartSource$read$3 = 0;
/*     */           long limit = MultipartReader.this.currentPartBytesRemaining(byteCount), l1 = (limit == 0L) ? -1L : MultipartReader.this.source.read(sink, limit);
/* 223 */           timeout1.timeout(originalTimeout$iv, TimeUnit.NANOSECONDS);
/* 224 */           if (other$iv.hasDeadline())
/* 225 */             timeout1.deadlineNanoTime(originalDeadline$iv);  return l1; } finally {} timeout1.timeout(originalTimeout$iv, TimeUnit.NANOSECONDS); if (other$iv.hasDeadline()) timeout1.deadlineNanoTime(originalDeadline$iv);  throw exception1;
/*     */       } 
/*     */ 
/*     */       
/* 229 */       if (other$iv.hasDeadline()) {
/* 230 */         timeout1.deadlineNanoTime(other$iv.deadlineNanoTime());
/*     */       }
/*     */       
/* 233 */       try { int $i$a$-intersectWith-MultipartReader$PartSource$read$3 = 0;
/*     */         long limit = MultipartReader.this.currentPartBytesRemaining(byteCount), l1 = (limit == 0L) ? -1L : MultipartReader.this.source.read(sink, limit);
/* 235 */         timeout1.timeout(originalTimeout$iv, TimeUnit.NANOSECONDS);
/* 236 */         if (other$iv.hasDeadline())
/* 237 */           timeout1.clearDeadline();  return l1; } finally {} timeout1.timeout(originalTimeout$iv, TimeUnit.NANOSECONDS); if (other$iv.hasDeadline()) timeout1.clearDeadline();  throw exception; }
/*     */ 
/*     */     
/*     */     private final Timeout timeout = new Timeout();
/*     */     
/*     */     public void close() {
/*     */       if (Intrinsics.areEqual(MultipartReader.this.currentPart, this))
/*     */         MultipartReader.this.currentPart = (PartSource)null; 
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public Timeout timeout() {
/*     */       return this.timeout;
/*     */     } }
/*     */ 
/*     */   
/*     */   private final long currentPartBytesRemaining(long maxResult) {
/*     */     this.source.require(this.crlfDashDashBoundary.size());
/*     */     long delimiterIndex = this.source.getBuffer().indexOf(this.crlfDashDashBoundary);
/*     */     long l1 = this.source.getBuffer().size() - this.crlfDashDashBoundary.size() + 1L;
/*     */     boolean bool2 = false;
/*     */     boolean bool1 = false;
/*     */     return (delimiterIndex == -1L) ? Math.min(maxResult, l1) : Math.min(maxResult, delimiterIndex);
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*     */     if (this.closed)
/*     */       return; 
/*     */     this.closed = true;
/*     */     this.currentPart = (PartSource)null;
/*     */     this.source.close();
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\036\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\020\002\n\000\030\0002\0020\001B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005¢\006\002\020\006J\t\020\t\032\0020\nH\001R\023\020\004\032\0020\0058\007¢\006\b\n\000\032\004\b\004\020\007R\023\020\002\032\0020\0038\007¢\006\b\n\000\032\004\b\002\020\b¨\006\013"}, d2 = {"Lokhttp3/MultipartReader$Part;", "Ljava/io/Closeable;", "headers", "Lokhttp3/Headers;", "body", "Lokio/BufferedSource;", "(Lokhttp3/Headers;Lokio/BufferedSource;)V", "()Lokio/BufferedSource;", "()Lokhttp3/Headers;", "close", "", "okhttp"})
/*     */   public static final class Part implements Closeable {
/*     */     @NotNull
/*     */     private final Headers headers;
/*     */     @NotNull
/*     */     private final BufferedSource body;
/*     */     
/*     */     public Part(@NotNull Headers headers, @NotNull BufferedSource body) {
/*     */       this.headers = headers;
/*     */       this.body = body;
/*     */     }
/*     */     
/*     */     @JvmName(name = "headers")
/*     */     @NotNull
/*     */     public final Headers headers() {
/*     */       return this.headers;
/*     */     }
/*     */     
/*     */     @JvmName(name = "body")
/*     */     @NotNull
/*     */     public final BufferedSource body() {
/*     */       return this.body;
/*     */     }
/*     */     
/*     */     public void close() {
/*     */       this.body.close();
/*     */     }
/*     */   }
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   static {
/*     */     afterBoundaryOptions = Options.Companion.of(new ByteString[] { ByteString.Companion.encodeUtf8("\r\n"), ByteString.Companion.encodeUtf8("--"), ByteString.Companion.encodeUtf8(" "), ByteString.Companion.encodeUtf8("\t") });
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\021\020\003\032\0020\004¢\006\b\n\000\032\004\b\005\020\006¨\006\007"}, d2 = {"Lokhttp3/MultipartReader$Companion;", "", "()V", "afterBoundaryOptions", "Lokio/Options;", "getAfterBoundaryOptions", "()Lokio/Options;", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */     
/*     */     @NotNull
/*     */     public final Options getAfterBoundaryOptions() {
/*     */       return MultipartReader.afterBoundaryOptions;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/MultipartReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */