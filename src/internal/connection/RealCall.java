/*     */ package okhttp3.internal.connection;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InterruptedIOException;
/*     */ import java.net.Socket;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import javax.net.ssl.HostnameVerifier;
/*     */ import javax.net.ssl.SSLSocketFactory;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.Unit;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.Address;
/*     */ import okhttp3.Call;
/*     */ import okhttp3.Callback;
/*     */ import okhttp3.CertificatePinner;
/*     */ import okhttp3.HttpUrl;
/*     */ import okhttp3.OkHttpClient;
/*     */ import okhttp3.Request;
/*     */ import okhttp3.Response;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.cache.CacheInterceptor;
/*     */ import okhttp3.internal.http.BridgeInterceptor;
/*     */ import okhttp3.internal.http.CallServerInterceptor;
/*     */ import okhttp3.internal.http.RealInterceptorChain;
/*     */ import okhttp3.internal.http.RetryAndFollowUpInterceptor;
/*     */ import okio.AsyncTimeout;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000§\001\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\020\000\n\002\b\004\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\013\n\002\b\004\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\006\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\013\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\006*\001.\030\0002\0020\001:\002deB\035\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007¢\006\002\020\bJ\016\0201\032\002022\006\020\020\032\0020\017J!\0203\032\002H4\"\n\b\000\0204*\004\030\001052\006\0206\032\002H4H\002¢\006\002\0207J\b\0208\032\00202H\002J\b\0209\032\00202H\026J\b\020:\032\0020\000H\026J\020\020;\032\0020<2\006\020=\032\0020>H\002J\020\020?\032\002022\006\020@\032\0020AH\026J\026\020B\032\002022\006\020C\032\0020\0052\006\020D\032\0020\007J\b\020E\032\0020FH\026J\025\020G\032\002022\006\020H\032\0020\007H\000¢\006\002\bIJ\r\020J\032\0020FH\000¢\006\002\bKJ\025\020L\032\0020\0362\006\020M\032\0020NH\000¢\006\002\bOJ\b\020P\032\0020\007H\026J\b\020Q\032\0020\007H\026J;\020R\032\002H4\"\n\b\000\0204*\004\030\001052\006\020\035\032\0020\0362\006\020S\032\0020\0072\006\020T\032\0020\0072\006\0206\032\002H4H\000¢\006\004\bU\020VJ\031\020W\032\004\030\001052\b\0206\032\004\030\00105H\000¢\006\002\bXJ\r\020Y\032\0020ZH\000¢\006\002\b[J\017\020\\\032\004\030\0010]H\000¢\006\002\b^J\b\020C\032\0020\005H\026J\006\020_\032\0020\007J\b\020-\032\0020`H\026J\006\0200\032\00202J!\020a\032\002H4\"\n\b\000\0204*\004\030\001052\006\020b\032\002H4H\002¢\006\002\0207J\b\020c\032\0020ZH\002R\020\020\t\032\004\030\0010\nX\016¢\006\002\n\000R\016\020\013\032\0020\007X\016¢\006\002\n\000R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\f\020\rR\"\020\020\032\004\030\0010\0172\b\020\016\032\004\030\0010\017@BX\016¢\006\b\n\000\032\004\b\021\020\022R\016\020\023\032\0020\024X\004¢\006\002\n\000R\034\020\025\032\004\030\0010\017X\016¢\006\016\n\000\032\004\b\026\020\022\"\004\b\027\020\030R\024\020\031\032\0020\032X\004¢\006\b\n\000\032\004\b\033\020\034R\020\020\035\032\004\030\0010\036X\016¢\006\002\n\000R\020\020\037\032\004\030\0010 X\016¢\006\002\n\000R\016\020!\032\0020\"X\004¢\006\002\n\000R\016\020#\032\0020\007X\016¢\006\002\n\000R\021\020\006\032\0020\007¢\006\b\n\000\032\004\b$\020%R\"\020&\032\004\030\0010\0362\b\020\016\032\004\030\0010\036@BX\016¢\006\b\n\000\032\004\b'\020(R\021\020\004\032\0020\005¢\006\b\n\000\032\004\b)\020*R\016\020+\032\0020\007X\016¢\006\002\n\000R\016\020,\032\0020\007X\016¢\006\002\n\000R\020\020-\032\0020.X\004¢\006\004\n\002\020/R\016\0200\032\0020\007X\016¢\006\002\n\000¨\006f"}, d2 = {"Lokhttp3/internal/connection/RealCall;", "Lokhttp3/Call;", "client", "Lokhttp3/OkHttpClient;", "originalRequest", "Lokhttp3/Request;", "forWebSocket", "", "(Lokhttp3/OkHttpClient;Lokhttp3/Request;Z)V", "callStackTrace", "", "canceled", "getClient", "()Lokhttp3/OkHttpClient;", "<set-?>", "Lokhttp3/internal/connection/RealConnection;", "connection", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "connectionToCancel", "getConnectionToCancel", "setConnectionToCancel", "(Lokhttp3/internal/connection/RealConnection;)V", "eventListener", "Lokhttp3/EventListener;", "getEventListener$okhttp", "()Lokhttp3/EventListener;", "exchange", "Lokhttp3/internal/connection/Exchange;", "exchangeFinder", "Lokhttp3/internal/connection/ExchangeFinder;", "executed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "expectMoreExchanges", "getForWebSocket", "()Z", "interceptorScopedExchange", "getInterceptorScopedExchange$okhttp", "()Lokhttp3/internal/connection/Exchange;", "getOriginalRequest", "()Lokhttp3/Request;", "requestBodyOpen", "responseBodyOpen", "timeout", "okhttp3/internal/connection/RealCall$timeout$1", "Lokhttp3/internal/connection/RealCall$timeout$1;", "timeoutEarlyExit", "acquireConnectionNoEvents", "", "callDone", "E", "Ljava/io/IOException;", "e", "(Ljava/io/IOException;)Ljava/io/IOException;", "callStart", "cancel", "clone", "createAddress", "Lokhttp3/Address;", "url", "Lokhttp3/HttpUrl;", "enqueue", "responseCallback", "Lokhttp3/Callback;", "enterNetworkInterceptorExchange", "request", "newExchangeFinder", "execute", "Lokhttp3/Response;", "exitNetworkInterceptorExchange", "closeExchange", "exitNetworkInterceptorExchange$okhttp", "getResponseWithInterceptorChain", "getResponseWithInterceptorChain$okhttp", "initExchange", "chain", "Lokhttp3/internal/http/RealInterceptorChain;", "initExchange$okhttp", "isCanceled", "isExecuted", "messageDone", "requestDone", "responseDone", "messageDone$okhttp", "(Lokhttp3/internal/connection/Exchange;ZZLjava/io/IOException;)Ljava/io/IOException;", "noMoreExchanges", "noMoreExchanges$okhttp", "redactedUrl", "", "redactedUrl$okhttp", "releaseConnectionNoEvents", "Ljava/net/Socket;", "releaseConnectionNoEvents$okhttp", "retryAfterFailure", "Lokio/AsyncTimeout;", "timeoutExit", "cause", "toLoggableString", "AsyncCall", "CallReference", "okhttp"})
/*     */ public final class RealCall implements Call {
/*     */   private final RealConnectionPool connectionPool;
/*     */   @NotNull
/*     */   private final EventListener eventListener;
/*     */   private final RealCall$timeout$1 timeout;
/*     */   private final AtomicBoolean executed;
/*     */   private Object callStackTrace;
/*     */   private ExchangeFinder exchangeFinder;
/*     */   @Nullable
/*     */   private RealConnection connection;
/*     */   private boolean timeoutEarlyExit;
/*     */   @Nullable
/*     */   private Exchange interceptorScopedExchange;
/*     */   private boolean requestBodyOpen;
/*     */   private boolean responseBodyOpen;
/*     */   private boolean expectMoreExchanges;
/*     */   private volatile boolean canceled;
/*     */   private volatile Exchange exchange;
/*     */   @Nullable
/*     */   private volatile RealConnection connectionToCancel;
/*     */   @NotNull
/*     */   private final OkHttpClient client;
/*     */   @NotNull
/*     */   private final Request originalRequest;
/*     */   private final boolean forWebSocket;
/*     */   
/*  60 */   public RealCall(@NotNull OkHttpClient client, @NotNull Request originalRequest, boolean forWebSocket) { this.client = client; this.originalRequest = originalRequest; this.forWebSocket = forWebSocket;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  66 */     this.connectionPool = this.client.connectionPool().getDelegate$okhttp();
/*     */     
/*  68 */     this.eventListener = this.client.eventListenerFactory().create(this);
/*     */     
/*  70 */     RealCall$timeout$1 realCall$timeout$11 = new RealCall$timeout$1();
/*     */ 
/*     */ 
/*     */     
/*  74 */     boolean bool1 = false, bool2 = false; RealCall$timeout$1 realCall$timeout$12 = realCall$timeout$11; RealCall realCall = this; int $i$a$-apply-RealCall$timeout$2 = 0;
/*  75 */     realCall$timeout$12.timeout(this.client.callTimeoutMillis(), TimeUnit.MILLISECONDS);
/*  76 */     Unit unit = Unit.INSTANCE; realCall.timeout = realCall$timeout$11;
/*     */     
/*  78 */     this.executed = new AtomicBoolean();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     this.expectMoreExchanges = true; } @NotNull public final OkHttpClient getClient() { return this.client; } @NotNull public final Request getOriginalRequest() { return this.originalRequest; }
/*     */   public final boolean getForWebSocket() { return this.forWebSocket; }
/*     */   @NotNull public final EventListener getEventListener$okhttp() { return this.eventListener; }
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\021\n\000\n\002\030\002\n\000\n\002\020\002\n\000*\001\000\b\n\030\0002\0020\001J\b\020\002\032\0020\003H\024¨\006\004"}, d2 = {"okhttp3/internal/connection/RealCall$timeout$1", "Lokio/AsyncTimeout;", "timedOut", "", "okhttp"}) public static final class RealCall$timeout$1 extends AsyncTimeout {
/*     */     protected void timedOut() { RealCall.this.cancel(); } }
/*     */   @Nullable public final RealConnection getConnection() { return this.connection; }
/*     */   @Nullable public final Exchange getInterceptorScopedExchange$okhttp() { return this.interceptorScopedExchange; }
/* 117 */   @Nullable public final RealConnection getConnectionToCancel() { return this.connectionToCancel; } public final void setConnectionToCancel(@Nullable RealConnection <set-?>) { this.connectionToCancel = <set-?>; } @NotNull
/*     */   public AsyncTimeout timeout() {
/* 119 */     return this.timeout;
/*     */   }
/*     */   @NotNull
/* 122 */   public RealCall clone() { return new RealCall(this.client, this.originalRequest, this.forWebSocket); } @NotNull
/*     */   public Request request() {
/* 124 */     return this.originalRequest;
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
/*     */   public void cancel() {
/* 136 */     if (this.canceled)
/*     */       return; 
/* 138 */     this.canceled = true;
/* 139 */     if (this.exchange != null) { this.exchange.cancel(); } else {  }
/* 140 */      if (this.connectionToCancel != null) { this.connectionToCancel.cancel(); }
/*     */     else {  }
/* 142 */      this.eventListener.canceled(this);
/*     */   }
/*     */   public boolean isCanceled() {
/* 145 */     return this.canceled;
/*     */   }
/*     */   @NotNull
/* 148 */   public Response execute() { boolean bool = this.executed.compareAndSet(false, true); boolean bool1 = false, bool2 = false; if (!bool)
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 552 */       int $i$a$-check-RealCall$execute$1 = 0; String str = "Already Executed"; throw (Throwable)new IllegalStateException(str.toString()); }  this.timeout.enter(); callStart(); try { this.client.dispatcher().executed$okhttp(this); return getResponseWithInterceptorChain$okhttp(); } finally { this.client.dispatcher().finished$okhttp(this); }  } public void enqueue(@NotNull Callback responseCallback) { Intrinsics.checkNotNullParameter(responseCallback, "responseCallback"); boolean bool = this.executed.compareAndSet(false, true); boolean bool1 = false, bool2 = false; if (!bool) { int $i$a$-check-RealCall$enqueue$1 = 0; String str = "Already Executed"; throw (Throwable)new IllegalStateException(str.toString()); }  callStart(); this.client.dispatcher().enqueue$okhttp(new AsyncCall(responseCallback)); } public boolean isExecuted() { return this.executed.get(); } private final void callStart() { this.callStackTrace = Platform.Companion.get().getStackTraceForCloseable("response.body().close()"); this.eventListener.callStart(this); } @NotNull public final Response getResponseWithInterceptorChain$okhttp() throws IOException { Exception exception; boolean bool1 = false; List<RetryAndFollowUpInterceptor> interceptors = new ArrayList(); List<RetryAndFollowUpInterceptor> list1 = interceptors; List list = this.client.interceptors(); boolean bool2 = false; CollectionsKt.addAll(list1, list); list1 = interceptors; RetryAndFollowUpInterceptor retryAndFollowUpInterceptor = new RetryAndFollowUpInterceptor(this.client); bool2 = false; list1.add(retryAndFollowUpInterceptor); list1 = interceptors; BridgeInterceptor bridgeInterceptor = new BridgeInterceptor(this.client.cookieJar()); bool2 = false; list1.add(bridgeInterceptor); list1 = interceptors; CacheInterceptor cacheInterceptor = new CacheInterceptor(this.client.cache()); bool2 = false; list1.add(cacheInterceptor); list1 = interceptors; ConnectInterceptor connectInterceptor = ConnectInterceptor.INSTANCE; bool2 = false; list1.add(connectInterceptor); if (!this.forWebSocket) { list1 = interceptors; List list2 = this.client.networkInterceptors(); bool2 = false; CollectionsKt.addAll(list1, list2); }  list1 = interceptors; CallServerInterceptor callServerInterceptor = new CallServerInterceptor(this.forWebSocket); bool2 = false; list1.add(callServerInterceptor); RealInterceptorChain chain = new RealInterceptorChain(this, interceptors, 0, null, this.originalRequest, this.client.connectTimeoutMillis(), this.client.readTimeoutMillis(), this.client.writeTimeoutMillis()); boolean calledNoMoreExchanges = false; try { Response response = chain.proceed(this.originalRequest); if (isCanceled()) { Util.closeQuietly((Closeable)response); throw (Throwable)new IOException("Canceled"); }  Response response1 = response; noMoreExchanges$okhttp(null); return response1; } catch (IOException e) { calledNoMoreExchanges = true; if (noMoreExchanges$okhttp((IOException)exception) == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");  throw (Throwable)noMoreExchanges$okhttp((IOException)exception); } finally {} if (!calledNoMoreExchanges) noMoreExchanges$okhttp(null);  throw exception; } public final void enterNetworkInterceptorExchange(@NotNull Request request, boolean newExchangeFinder) { Intrinsics.checkNotNullParameter(request, "request"); boolean bool1 = (this.interceptorScopedExchange == null) ? true : false, bool2 = false; null = false; null = false; boolean bool3 = false; if (!bool1) { boolean bool = false; String str = "Check failed."; throw (Throwable)new IllegalStateException(str.toString()); }  RealCall realCall = this; bool2 = false; synchronized (false) { int $i$a$-synchronized-RealCall$enterNetworkInterceptorExchange$1 = 0; boolean bool4 = !this.responseBodyOpen ? true : false, bool5 = false, bool6 = false; if (!bool4) { int $i$a$-check-RealCall$enterNetworkInterceptorExchange$1$1 = 0; String str = "cannot make a new request because the previous response is still open: please call response.close()"; throw (Throwable)new IllegalStateException(str.toString()); }  bool4 = !this.requestBodyOpen ? true : false; bool5 = false; bool6 = false; bool6 = false; boolean bool7 = false; if (!bool4) { boolean bool = false; String str = "Check failed."; throw (Throwable)new IllegalStateException(str.toString()); }  Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealCall}, name=null} */ }  if (newExchangeFinder) this.exchangeFinder = new ExchangeFinder(this.connectionPool, createAddress(request.url()), this, this.eventListener);  } @NotNull public final Exchange initExchange$okhttp(@NotNull RealInterceptorChain chain) { Intrinsics.checkNotNullParameter(chain, "chain"); RealCall realCall1 = this; boolean bool1 = false; synchronized (false) { int $i$a$-synchronized-RealCall$initExchange$1 = 0; boolean bool = this.expectMoreExchanges; boolean bool3 = false, bool4 = false; if (!bool) { int $i$a$-check-RealCall$initExchange$1$1 = 0; String str = "released"; throw (Throwable)new IllegalStateException(str.toString()); }  bool = !this.responseBodyOpen; bool3 = false; bool4 = false; bool4 = false; boolean bool5 = false; if (!bool) { boolean bool6 = false; String str = "Check failed."; throw (Throwable)new IllegalStateException(str.toString()); }  bool = !this.requestBodyOpen; bool3 = false; bool4 = false; bool4 = false; bool5 = false; if (!bool) { boolean bool6 = false; String str = "Check failed."; throw (Throwable)new IllegalStateException(str.toString()); }  Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealCall}, name=null} */ }  Intrinsics.checkNotNull(this.exchangeFinder); ExchangeFinder exchangeFinder = this.exchangeFinder; ExchangeCodec codec = exchangeFinder.find(this.client, chain); Exchange result = new Exchange(this, this.eventListener, exchangeFinder, codec); this.interceptorScopedExchange = result; this.exchange = result; RealCall realCall2 = this; boolean bool2 = false; synchronized (false) { int $i$a$-synchronized-RealCall$initExchange$2 = 0; this.requestBodyOpen = true; this.responseBodyOpen = true; Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealCall}, name=null} */ }  if (this.canceled)
/* 553 */       throw (Throwable)new IOException("Canceled");  return result; } public final void acquireConnectionNoEvents(@NotNull RealConnection connection) { Intrinsics.checkNotNullParameter(connection, "connection"); Object $this$assertThreadHoldsLock$iv = connection; int $i$f$assertThreadHoldsLock = 0; if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv))
/* 554 */     { Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); }  boolean bool1 = (this.connection == null) ? true : false; $i$f$assertThreadHoldsLock = 0; boolean bool2 = false; bool2 = false; boolean bool3 = false; if (!bool1) { boolean bool = false; String str = "Check failed."; throw (Throwable)new IllegalStateException(str.toString()); }  this.connection = connection; connection.getCalls().add(new CallReference(this, this.callStackTrace)); }
/*     */   public final <E extends IOException> E messageDone$okhttp(@NotNull Exchange exchange, boolean requestDone, boolean responseDone, IOException e) { Intrinsics.checkNotNullParameter(exchange, "exchange"); if ((Intrinsics.areEqual(exchange, this.exchange) ^ true) != 0) return (E)e;  boolean bothStreamsDone = false; boolean callDone = false; RealCall realCall = this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-RealCall$messageDone$1 = 0; if ((requestDone && this.requestBodyOpen) || (responseDone && this.responseBodyOpen)) { if (requestDone) this.requestBodyOpen = false;  if (responseDone) this.responseBodyOpen = false;  bothStreamsDone = (!this.requestBodyOpen && !this.responseBodyOpen); callDone = (!this.requestBodyOpen && !this.responseBodyOpen && !this.expectMoreExchanges); }  Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealCall}, name=null} */ }  if (bothStreamsDone) { this.exchange = (Exchange)null; if (this.connection != null) { this.connection.incrementSuccessCount$okhttp(); } else {  }  }  if (callDone) return callDone((E)e);  return (E)e; }
/*     */   @Nullable public final IOException noMoreExchanges$okhttp(@Nullable IOException e) { boolean callDone = false; RealCall realCall = this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-RealCall$noMoreExchanges$1 = 0; if (this.expectMoreExchanges) { this.expectMoreExchanges = false; callDone = (!this.requestBodyOpen && !this.responseBodyOpen); }  Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealCall}, name=null} */ }  if (callDone) return callDone(e);  return e; }
/*     */   @Nullable public final Socket releaseConnectionNoEvents$okhttp() { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield connection : Lokhttp3/internal/connection/RealConnection;
/*     */     //   4: dup
/*     */     //   5: invokestatic checkNotNull : (Ljava/lang/Object;)V
/*     */     //   8: astore_1
/*     */     //   9: aload_1
/*     */     //   10: astore_2
/*     */     //   11: iconst_0
/*     */     //   12: istore_3
/*     */     //   13: getstatic okhttp3/internal/Util.assertionsEnabled : Z
/*     */     //   16: ifeq -> 79
/*     */     //   19: aload_2
/*     */     //   20: invokestatic holdsLock : (Ljava/lang/Object;)Z
/*     */     //   23: ifne -> 79
/*     */     //   26: new java/lang/AssertionError
/*     */     //   29: dup
/*     */     //   30: new java/lang/StringBuilder
/*     */     //   33: dup
/*     */     //   34: invokespecial <init> : ()V
/*     */     //   37: ldc_w 'Thread '
/*     */     //   40: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   43: invokestatic currentThread : ()Ljava/lang/Thread;
/*     */     //   46: dup
/*     */     //   47: ldc_w 'Thread.currentThread()'
/*     */     //   50: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */     //   53: invokevirtual getName : ()Ljava/lang/String;
/*     */     //   56: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   59: ldc_w ' MUST hold lock on '
/*     */     //   62: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   65: aload_2
/*     */     //   66: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*     */     //   69: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   72: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   75: checkcast java/lang/Throwable
/*     */     //   78: athrow
/*     */     //   79: nop
/*     */     //   80: aload_1
/*     */     //   81: invokevirtual getCalls : ()Ljava/util/List;
/*     */     //   84: astore_2
/*     */     //   85: aload_2
/*     */     //   86: astore #4
/*     */     //   88: iconst_0
/*     */     //   89: istore #5
/*     */     //   91: iconst_0
/*     */     //   92: istore #6
/*     */     //   94: aload #4
/*     */     //   96: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   101: astore #7
/*     */     //   103: aload #7
/*     */     //   105: invokeinterface hasNext : ()Z
/*     */     //   110: ifeq -> 161
/*     */     //   113: aload #7
/*     */     //   115: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   120: astore #8
/*     */     //   122: aload #8
/*     */     //   124: checkcast java/lang/ref/Reference
/*     */     //   127: astore #9
/*     */     //   129: iconst_0
/*     */     //   130: istore #10
/*     */     //   132: aload #9
/*     */     //   134: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   137: checkcast okhttp3/internal/connection/RealCall
/*     */     //   140: aload_0
/*     */     //   141: checkcast okhttp3/internal/connection/RealCall
/*     */     //   144: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   147: ifeq -> 155
/*     */     //   150: iload #6
/*     */     //   152: goto -> 162
/*     */     //   155: iinc #6, 1
/*     */     //   158: goto -> 103
/*     */     //   161: iconst_m1
/*     */     //   162: istore_3
/*     */     //   163: iload_3
/*     */     //   164: iconst_m1
/*     */     //   165: if_icmpeq -> 172
/*     */     //   168: iconst_1
/*     */     //   169: goto -> 173
/*     */     //   172: iconst_0
/*     */     //   173: istore #4
/*     */     //   175: iconst_0
/*     */     //   176: istore #5
/*     */     //   178: iconst_0
/*     */     //   179: istore #6
/*     */     //   181: iconst_0
/*     */     //   182: istore #6
/*     */     //   184: iconst_0
/*     */     //   185: istore #7
/*     */     //   187: iload #4
/*     */     //   189: ifne -> 216
/*     */     //   192: iconst_0
/*     */     //   193: istore #8
/*     */     //   195: ldc_w 'Check failed.'
/*     */     //   198: astore #7
/*     */     //   200: new java/lang/IllegalStateException
/*     */     //   203: dup
/*     */     //   204: aload #7
/*     */     //   206: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   209: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   212: checkcast java/lang/Throwable
/*     */     //   215: athrow
/*     */     //   216: aload_2
/*     */     //   217: iload_3
/*     */     //   218: invokeinterface remove : (I)Ljava/lang/Object;
/*     */     //   223: pop
/*     */     //   224: aload_0
/*     */     //   225: aconst_null
/*     */     //   226: checkcast okhttp3/internal/connection/RealConnection
/*     */     //   229: putfield connection : Lokhttp3/internal/connection/RealConnection;
/*     */     //   232: aload_2
/*     */     //   233: invokeinterface isEmpty : ()Z
/*     */     //   238: ifeq -> 264
/*     */     //   241: aload_1
/*     */     //   242: invokestatic nanoTime : ()J
/*     */     //   245: invokevirtual setIdleAtNs$okhttp : (J)V
/*     */     //   248: aload_0
/*     */     //   249: getfield connectionPool : Lokhttp3/internal/connection/RealConnectionPool;
/*     */     //   252: aload_1
/*     */     //   253: invokevirtual connectionBecameIdle : (Lokhttp3/internal/connection/RealConnection;)Z
/*     */     //   256: ifeq -> 264
/*     */     //   259: aload_1
/*     */     //   260: invokevirtual socket : ()Ljava/net/Socket;
/*     */     //   263: areturn
/*     */     //   264: aconst_null
/*     */     //   265: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #374	-> 0
/*     */     //   #375	-> 9
/*     */     //   #565	-> 13
/*     */     //   #566	-> 26
/*     */     //   #568	-> 79
/*     */     //   #377	-> 80
/*     */     //   #378	-> 85
/*     */     //   #569	-> 91
/*     */     //   #570	-> 94
/*     */     //   #570	-> 103
/*     */     //   #571	-> 122
/*     */     //   #378	-> 132
/*     */     //   #572	-> 150
/*     */     //   #573	-> 155
/*     */     //   #570	-> 158
/*     */     //   #575	-> 161
/*     */     //   #378	-> 162
/*     */     //   #379	-> 163
/*     */     //   #381	-> 216
/*     */     //   #382	-> 224
/*     */     //   #384	-> 232
/*     */     //   #385	-> 241
/*     */     //   #386	-> 248
/*     */     //   #387	-> 259
/*     */     //   #391	-> 264
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   11	69	2	$this$assertThreadHoldsLock$iv	Ljava/lang/Object;
/*     */     //   13	67	3	$i$f$assertThreadHoldsLock	I
/*     */     //   129	18	9	it	Ljava/lang/ref/Reference;
/*     */     //   132	15	10	$i$a$-indexOfFirst-RealCall$releaseConnectionNoEvents$index$1	I
/*     */     //   122	36	8	item$iv	Ljava/lang/Object;
/*     */     //   94	68	6	index$iv	I
/*     */     //   88	74	4	$this$indexOfFirst$iv	Ljava/util/List;
/*     */     //   91	71	5	$i$f$indexOfFirst	I
/*     */     //   163	103	3	index	I
/*     */     //   85	181	2	calls	Ljava/util/List;
/*     */     //   9	257	1	connection	Lokhttp3/internal/connection/RealConnection;
/* 557 */     //   0	266	0	this	Lokhttp3/internal/connection/RealCall; } private final <E extends IOException> E timeoutExit(IOException cause) { if (this.timeoutEarlyExit) return (E)cause;  if (!this.timeout.exit()) return (E)cause;  InterruptedIOException e = new InterruptedIOException("timeout"); if (cause != null) e.initCause(cause);  return (E)e; } public final void timeoutEarlyExit() { boolean bool1 = !this.timeoutEarlyExit ? true : false, bool2 = false, bool3 = false; bool3 = false; boolean bool4 = false; if (!bool1) { boolean bool = false; String str = "Check failed."; throw (Throwable)new IllegalStateException(str.toString()); }  this.timeoutEarlyExit = true; this.timeout.exit(); } public final void exitNetworkInterceptorExchange$okhttp(boolean closeExchange) { RealCall realCall = this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-RealCall$exitNetworkInterceptorExchange$1 = 0; boolean bool1 = this.expectMoreExchanges; boolean bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-RealCall$exitNetworkInterceptorExchange$1$1 = 0; String str = "released"; throw (Throwable)new IllegalStateException(str.toString()); }  Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealCall}, name=null} */ }  if (closeExchange) if (this.exchange != null) { this.exchange.detachWithViolence(); } else {  }   this.interceptorScopedExchange = (Exchange)null; } private final <E extends IOException> E callDone(IOException e) { Object $this$assertThreadDoesntHoldLock$iv = this; int $i$f$assertThreadDoesntHoldLock = 0; if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) {
/* 558 */       Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv);
/*     */     }  RealConnection connection = this.connection; if (connection != null)
/*     */     { Object object = connection; int i = 0;
/* 561 */       if (Util.assertionsEnabled && Thread.holdsLock(object))
/* 562 */       { Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + object); }  i = 0; synchronized (false) { int $i$a$-synchronized-RealCall$callDone$socket$1 = 0; Socket socket1 = releaseConnectionNoEvents$okhttp(); /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealConnection}, name=connection} */ }  Socket socket = socket1; if (this.connection == null) { if (socket != null) { Util.closeQuietly(socket); } else {  }  this.eventListener.connectionReleased(this, connection); } else { i = (socket == null) ? 1 : 0; boolean bool1 = false, bool2 = false; bool2 = false; boolean bool3 = false; if (i == 0) { boolean bool = false; String str = "Check failed."; throw (Throwable)new IllegalStateException(str.toString()); }  }  }  IOException result = timeoutExit(e); if (e != null) { Intrinsics.checkNotNull(result); this.eventListener.callFailed(this, result); } else { this.eventListener.callEnd(this); }  return (E)result; } private final Address createAddress(HttpUrl url) { SSLSocketFactory sslSocketFactory = (SSLSocketFactory)null; HostnameVerifier hostnameVerifier = (HostnameVerifier)null; CertificatePinner certificatePinner = (CertificatePinner)null; if (url.isHttps()) { sslSocketFactory = this.client.sslSocketFactory(); hostnameVerifier = this.client.hostnameVerifier(); certificatePinner = this.client.certificatePinner(); }  return new Address(url.host(), url.port(), this.client.dns(), this.client.socketFactory(), sslSocketFactory, hostnameVerifier, certificatePinner, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector()); } public final boolean retryAfterFailure() { Intrinsics.checkNotNull(this.exchangeFinder); return this.exchangeFinder.retryAfterFailure(); } private final String toLoggableString() { return (isCanceled() ? "canceled " : "") + (this.forWebSocket ? "web socket" : "call") + " to " + redactedUrl$okhttp(); } @NotNull public final String redactedUrl$okhttp() { return this.originalRequest.url().redact(); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000@\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\004\n\002\020\016\n\002\b\003\n\002\030\002\n\002\b\003\n\002\020\002\n\000\n\002\030\002\n\002\b\004\b\004\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\016\020\026\032\0020\0272\006\020\030\032\0020\031J\022\020\032\032\0020\0272\n\020\033\032\0060\000R\0020\006J\b\020\034\032\0020\027H\026R\021\020\005\032\0020\0068F¢\006\006\032\004\b\007\020\bR\036\020\013\032\0020\n2\006\020\t\032\0020\n@BX\016¢\006\b\n\000\032\004\b\f\020\rR\021\020\016\032\0020\0178F¢\006\006\032\004\b\020\020\021R\021\020\022\032\0020\0238F¢\006\006\032\004\b\024\020\025R\016\020\002\032\0020\003X\004¢\006\002\n\000¨\006\035"}, d2 = {"Lokhttp3/internal/connection/RealCall$AsyncCall;", "Ljava/lang/Runnable;", "responseCallback", "Lokhttp3/Callback;", "(Lokhttp3/internal/connection/RealCall;Lokhttp3/Callback;)V", "call", "Lokhttp3/internal/connection/RealCall;", "getCall", "()Lokhttp3/internal/connection/RealCall;", "<set-?>", "Ljava/util/concurrent/atomic/AtomicInteger;", "callsPerHost", "getCallsPerHost", "()Ljava/util/concurrent/atomic/AtomicInteger;", "host", "", "getHost", "()Ljava/lang/String;", "request", "Lokhttp3/Request;", "getRequest", "()Lokhttp3/Request;", "executeOn", "", "executorService", "Ljava/util/concurrent/ExecutorService;", "reuseCallsPerHostFrom", "other", "run", "okhttp"}) public final class AsyncCall implements Runnable { public void run() { String name$iv = "OkHttp " + RealCall.this.redactedUrl$okhttp(); int $i$f$threadName = 0; Thread currentThread$iv = Thread.currentThread(); Intrinsics.checkNotNullExpressionValue(currentThread$iv, "currentThread"); String oldName$iv = currentThread$iv.getName(); currentThread$iv.setName(name$iv); try { int $i$a$-threadName-RealCall$AsyncCall$run$1 = 0; boolean signalledCallback = false; RealCall.this.timeout.enter(); try { Response response = RealCall.this.getResponseWithInterceptorChain$okhttp(); signalledCallback = true; this.responseCallback.onResponse(RealCall.this, response); } catch (IOException e) { if (signalledCallback) { Platform.Companion.get().log("Callback failure for " + RealCall.this.toLoggableString(), 4, e); } else { this.responseCallback.onFailure(RealCall.this, e); }  } catch (Throwable t) { RealCall.this.cancel(); if (!signalledCallback) { IOException canceledException = new IOException("canceled due to " + t); ExceptionsKt.addSuppressed(canceledException, t); this.responseCallback.onFailure(RealCall.this, canceledException); }  throw t; } finally { RealCall.this.getClient().dispatcher().finished$okhttp(this); }  } finally { currentThread$iv.setName(oldName$iv); }
/*     */        }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     private volatile AtomicInteger callsPerHost;
/*     */     private final Callback responseCallback;
/*     */     
/*     */     public AsyncCall(Callback responseCallback) {
/*     */       this.responseCallback = responseCallback;
/*     */       this.callsPerHost = new AtomicInteger(0);
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final AtomicInteger getCallsPerHost() {
/*     */       return this.callsPerHost;
/*     */     }
/*     */     
/*     */     public final void reuseCallsPerHostFrom(@NotNull AsyncCall other) {
/*     */       Intrinsics.checkNotNullParameter(other, "other");
/*     */       this.callsPerHost = other.callsPerHost;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final String getHost() {
/*     */       return RealCall.this.getOriginalRequest().url().host();
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Request getRequest() {
/*     */       return RealCall.this.getOriginalRequest();
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final RealCall getCall() {
/*     */       return RealCall.this;
/*     */     }
/*     */     
/*     */     public final void executeOn(@NotNull ExecutorService executorService) {
/*     */       Intrinsics.checkNotNullParameter(executorService, "executorService");
/*     */       Object $this$assertThreadDoesntHoldLock$iv = RealCall.this.getClient().dispatcher();
/*     */       int $i$f$assertThreadDoesntHoldLock = 0;
/*     */       if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) {
/*     */         Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()");
/*     */         throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv);
/*     */       } 
/*     */       boolean success = false;
/*     */       try {
/*     */         executorService.execute(this);
/*     */         success = true;
/*     */       } catch (RejectedExecutionException e) {
/*     */         InterruptedIOException ioException = new InterruptedIOException("executor rejected");
/*     */         ioException.initCause(e);
/*     */         RealCall.this.noMoreExchanges$okhttp(ioException);
/*     */         this.responseCallback.onFailure(RealCall.this, ioException);
/*     */       } finally {
/*     */         RealCall.this.getClient().dispatcher().finished$okhttp(this);
/*     */       } 
/*     */     } }
/*     */ 
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\030\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\000\n\002\b\004\b\000\030\0002\b\022\004\022\0020\0020\001B\027\022\006\020\003\032\0020\002\022\b\020\004\032\004\030\0010\005¢\006\002\020\006R\023\020\004\032\004\030\0010\005¢\006\b\n\000\032\004\b\007\020\b¨\006\t"}, d2 = {"Lokhttp3/internal/connection/RealCall$CallReference;", "Ljava/lang/ref/WeakReference;", "Lokhttp3/internal/connection/RealCall;", "referent", "callStackTrace", "", "(Lokhttp3/internal/connection/RealCall;Ljava/lang/Object;)V", "getCallStackTrace", "()Ljava/lang/Object;", "okhttp"})
/*     */   public static final class CallReference extends WeakReference<RealCall> {
/*     */     @Nullable
/*     */     private final Object callStackTrace;
/*     */     
/*     */     public CallReference(@NotNull RealCall referent, @Nullable Object callStackTrace) {
/*     */       super(referent);
/*     */       this.callStackTrace = callStackTrace;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public final Object getCallStackTrace() {
/*     */       return this.callStackTrace;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/connection/RealCall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */