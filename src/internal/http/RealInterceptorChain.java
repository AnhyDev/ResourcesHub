/*     */ package okhttp3.internal.http;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.Call;
/*     */ import okhttp3.Connection;
/*     */ import okhttp3.Interceptor;
/*     */ import okhttp3.Request;
/*     */ import okhttp3.Response;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.connection.Exchange;
/*     */ import okhttp3.internal.connection.RealCall;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000L\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\000\n\002\020\b\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\017\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\030\0002\0020\001BM\022\006\020\002\032\0020\003\022\f\020\004\032\b\022\004\022\0020\0060\005\022\006\020\007\032\0020\b\022\b\020\t\032\004\030\0010\n\022\006\020\013\032\0020\f\022\006\020\r\032\0020\b\022\006\020\016\032\0020\b\022\006\020\017\032\0020\b¢\006\002\020\020J\b\020\002\032\0020\034H\026J\b\020\r\032\0020\bH\026J\n\020\035\032\004\030\0010\036H\026JK\020\037\032\0020\0002\b\b\002\020\007\032\0020\b2\n\b\002\020\t\032\004\030\0010\n2\b\b\002\020\013\032\0020\f2\b\b\002\020\r\032\0020\b2\b\b\002\020\016\032\0020\b2\b\b\002\020\017\032\0020\bH\000¢\006\002\b J\020\020!\032\0020\"2\006\020\013\032\0020\fH\026J\b\020\016\032\0020\bH\026J\b\020\013\032\0020\fH\026J\030\020#\032\0020\0012\006\020$\032\0020\b2\006\020%\032\0020&H\026J\030\020'\032\0020\0012\006\020$\032\0020\b2\006\020%\032\0020&H\026J\030\020(\032\0020\0012\006\020$\032\0020\b2\006\020%\032\0020&H\026J\b\020\017\032\0020\bH\026R\024\020\002\032\0020\003X\004¢\006\b\n\000\032\004\b\021\020\022R\016\020\023\032\0020\bX\016¢\006\002\n\000R\024\020\r\032\0020\bX\004¢\006\b\n\000\032\004\b\024\020\025R\026\020\t\032\004\030\0010\nX\004¢\006\b\n\000\032\004\b\026\020\027R\016\020\007\032\0020\bX\004¢\006\002\n\000R\024\020\004\032\b\022\004\022\0020\0060\005X\004¢\006\002\n\000R\024\020\016\032\0020\bX\004¢\006\b\n\000\032\004\b\030\020\025R\024\020\013\032\0020\fX\004¢\006\b\n\000\032\004\b\031\020\032R\024\020\017\032\0020\bX\004¢\006\b\n\000\032\004\b\033\020\025¨\006)"}, d2 = {"Lokhttp3/internal/http/RealInterceptorChain;", "Lokhttp3/Interceptor$Chain;", "call", "Lokhttp3/internal/connection/RealCall;", "interceptors", "", "Lokhttp3/Interceptor;", "index", "", "exchange", "Lokhttp3/internal/connection/Exchange;", "request", "Lokhttp3/Request;", "connectTimeoutMillis", "readTimeoutMillis", "writeTimeoutMillis", "(Lokhttp3/internal/connection/RealCall;Ljava/util/List;ILokhttp3/internal/connection/Exchange;Lokhttp3/Request;III)V", "getCall$okhttp", "()Lokhttp3/internal/connection/RealCall;", "calls", "getConnectTimeoutMillis$okhttp", "()I", "getExchange$okhttp", "()Lokhttp3/internal/connection/Exchange;", "getReadTimeoutMillis$okhttp", "getRequest$okhttp", "()Lokhttp3/Request;", "getWriteTimeoutMillis$okhttp", "Lokhttp3/Call;", "connection", "Lokhttp3/Connection;", "copy", "copy$okhttp", "proceed", "Lokhttp3/Response;", "withConnectTimeout", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "withReadTimeout", "withWriteTimeout", "okhttp"})
/*     */ public final class RealInterceptorChain
/*     */   implements Interceptor.Chain {
/*     */   private int calls;
/*     */   @NotNull
/*     */   private final RealCall call;
/*     */   private final List<Interceptor> interceptors;
/*     */   private final int index;
/*     */   @Nullable
/*     */   private final Exchange exchange;
/*     */   @NotNull
/*     */   private final Request request;
/*     */   private final int connectTimeoutMillis;
/*     */   private final int readTimeoutMillis;
/*     */   private final int writeTimeoutMillis;
/*     */   
/*     */   public RealInterceptorChain(@NotNull RealCall call, @NotNull List<Interceptor> interceptors, int index, @Nullable Exchange exchange, @NotNull Request request, int connectTimeoutMillis, int readTimeoutMillis, int writeTimeoutMillis) {
/*  36 */     this.call = call; this.interceptors = interceptors; this.index = index; this.exchange = exchange; this.request = request; this.connectTimeoutMillis = connectTimeoutMillis; this.readTimeoutMillis = readTimeoutMillis; this.writeTimeoutMillis = writeTimeoutMillis; } @NotNull
/*  37 */   public final RealCall getCall$okhttp() { return this.call; }
/*     */   
/*     */   @Nullable
/*  40 */   public final Exchange getExchange$okhttp() { return this.exchange; } @NotNull
/*  41 */   public final Request getRequest$okhttp() { return this.request; }
/*  42 */   public final int getConnectTimeoutMillis$okhttp() { return this.connectTimeoutMillis; }
/*  43 */   public final int getReadTimeoutMillis$okhttp() { return this.readTimeoutMillis; } public final int getWriteTimeoutMillis$okhttp() {
/*  44 */     return this.writeTimeoutMillis;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final RealInterceptorChain copy$okhttp(int index, @Nullable Exchange exchange, @NotNull Request request, int connectTimeoutMillis, int readTimeoutMillis, int writeTimeoutMillis)
/*     */   {
/*  56 */     Intrinsics.checkNotNullParameter(request, "request"); return new RealInterceptorChain(this.call, this.interceptors, index, exchange, request, connectTimeoutMillis, 
/*  57 */         readTimeoutMillis, writeTimeoutMillis); } @Nullable
/*     */   public Connection connection() {
/*  59 */     return (this.exchange != null) ? (Connection)this.exchange.getConnection$okhttp() : null;
/*     */   } public int connectTimeoutMillis() {
/*  61 */     return this.connectTimeoutMillis;
/*     */   }
/*     */   @NotNull
/*  64 */   public Interceptor.Chain withConnectTimeout(int timeout, @NotNull TimeUnit unit) { Intrinsics.checkNotNullParameter(unit, "unit"); boolean bool1 = (this.exchange == null) ? true : false, bool2 = false, bool3 = false; if (!bool1)
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
/* 124 */       int $i$a$-check-RealInterceptorChain$withConnectTimeout$1 = 0; String str = "Timeouts can't be adjusted in a network interceptor"; throw (Throwable)new IllegalStateException(str.toString()); }  return copy$okhttp$default(this, 0, null, null, Util.checkDuration("connectTimeout", timeout, unit), 0, 0, 55, null); } public int readTimeoutMillis() { return this.readTimeoutMillis; } @NotNull public Interceptor.Chain withReadTimeout(int timeout, @NotNull TimeUnit unit) { Intrinsics.checkNotNullParameter(unit, "unit"); boolean bool1 = (this.exchange == null) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-RealInterceptorChain$withReadTimeout$1 = 0; String str = "Timeouts can't be adjusted in a network interceptor"; throw (Throwable)new IllegalStateException(str.toString()); }  return copy$okhttp$default(this, 0, null, null, 0, Util.checkDuration("readTimeout", timeout, unit), 0, 47, null); } public int writeTimeoutMillis() { return this.writeTimeoutMillis; } @NotNull public Interceptor.Chain withWriteTimeout(int timeout, @NotNull TimeUnit unit) { Intrinsics.checkNotNullParameter(unit, "unit"); boolean bool1 = (this.exchange == null) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-RealInterceptorChain$withWriteTimeout$1 = 0; String str = "Timeouts can't be adjusted in a network interceptor"; throw (Throwable)new IllegalStateException(str.toString()); }  return copy$okhttp$default(this, 0, null, null, 0, 0, Util.checkDuration("writeTimeout", timeout, unit), 31, null); } @NotNull public Response proceed(@NotNull Request request) throws IOException { Intrinsics.checkNotNullParameter(request, "request"); int i = (this.index < this.interceptors.size()) ? 1 : 0; boolean bool1 = false, bool2 = false; bool2 = false; boolean bool3 = false; if (!i) { boolean bool = false; String str = "Check failed."; throw (Throwable)new IllegalStateException(str.toString()); }  this.calls = (i = this.calls) + 1; if (this.exchange != null) { boolean bool = this.exchange.getFinder$okhttp().sameHostAndPort(request.url()); bool1 = false; bool2 = false; if (!bool) { int $i$a$-check-RealInterceptorChain$proceed$1 = 0; String str = "network interceptor " + (Interceptor)this.interceptors.get(this.index - 1) + " must retain the same host and port"; throw (Throwable)new IllegalStateException(str.toString()); }  bool = (this.calls == 1); bool1 = false; bool2 = false; if (!bool) { int $i$a$-check-RealInterceptorChain$proceed$2 = 0; String str = "network interceptor " + (Interceptor)this.interceptors.get(this.index - 1) + " must call proceed() exactly once"; throw (Throwable)new IllegalStateException(str.toString()); }  }  RealInterceptorChain next = copy$okhttp$default(this, this.index + 1, null, request, 0, 0, 0, 58, null); Interceptor interceptor = this.interceptors.get(this.index); if (interceptor.intercept(next) != null) { Response response = interceptor.intercept(next); if (this.exchange != null) { bool3 = (this.index + 1 >= this.interceptors.size() || next.calls == 1) ? true : false; boolean bool6 = false, bool7 = false; if (!bool3) { int $i$a$-check-RealInterceptorChain$proceed$3 = 0; String str = "network interceptor " + interceptor + " must call proceed() exactly once"; throw (Throwable)new IllegalStateException(str.toString()); }  }  bool3 = (response.body() != null) ? true : false; boolean bool4 = false, bool5 = false; if (!bool3) { int $i$a$-check-RealInterceptorChain$proceed$4 = 0;
/*     */         String str = "interceptor " + interceptor + " returned a response with no body";
/*     */         throw (Throwable)new IllegalStateException(str.toString()); }
/*     */       
/*     */       return response; }
/*     */     
/*     */     interceptor.intercept(next);
/*     */     throw (Throwable)new NullPointerException("interceptor " + interceptor + " returned null"); }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Call call() {
/*     */     return (Call)this.call;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Request request() {
/*     */     return this.request;
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http/RealInterceptorChain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */