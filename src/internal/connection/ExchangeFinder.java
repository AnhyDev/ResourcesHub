/*     */ package okhttp3.internal.connection;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.Socket;
/*     */ import java.util.List;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.Unit;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.Address;
/*     */ import okhttp3.EventListener;
/*     */ import okhttp3.HttpUrl;
/*     */ import okhttp3.OkHttpClient;
/*     */ import okhttp3.Route;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.http.ExchangeCodec;
/*     */ import okhttp3.internal.http.RealInterceptorChain;
/*     */ import okhttp3.internal.http2.ErrorCode;
/*     */ import okhttp3.internal.http2.StreamResetException;
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
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000r\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\020\b\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\005\n\002\020\013\n\002\b\006\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\000\030\0002\0020\001B%\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007\022\006\020\b\032\0020\t¢\006\002\020\nJ\026\020\027\032\0020\0302\006\020\031\032\0020\0322\006\020\033\032\0020\034J0\020\035\032\0020\0362\006\020\037\032\0020\0162\006\020 \032\0020\0162\006\020!\032\0020\0162\006\020\"\032\0020\0162\006\020#\032\0020$H\002J8\020%\032\0020\0362\006\020\037\032\0020\0162\006\020 \032\0020\0162\006\020!\032\0020\0162\006\020\"\032\0020\0162\006\020#\032\0020$2\006\020&\032\0020$H\002J\006\020'\032\0020$J\n\020(\032\004\030\0010\020H\002J\016\020)\032\0020$2\006\020*\032\0020+J\016\020,\032\0020-2\006\020.\032\0020/R\024\020\004\032\0020\005X\004¢\006\b\n\000\032\004\b\013\020\fR\016\020\006\032\0020\007X\004¢\006\002\n\000R\016\020\002\032\0020\003X\004¢\006\002\n\000R\016\020\r\032\0020\016X\016¢\006\002\n\000R\016\020\b\032\0020\tX\004¢\006\002\n\000R\020\020\017\032\004\030\0010\020X\016¢\006\002\n\000R\016\020\021\032\0020\016X\016¢\006\002\n\000R\016\020\022\032\0020\016X\016¢\006\002\n\000R\020\020\023\032\004\030\0010\024X\016¢\006\002\n\000R\020\020\025\032\004\030\0010\026X\016¢\006\002\n\000¨\0060"}, d2 = {"Lokhttp3/internal/connection/ExchangeFinder;", "", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "address", "Lokhttp3/Address;", "call", "Lokhttp3/internal/connection/RealCall;", "eventListener", "Lokhttp3/EventListener;", "(Lokhttp3/internal/connection/RealConnectionPool;Lokhttp3/Address;Lokhttp3/internal/connection/RealCall;Lokhttp3/EventListener;)V", "getAddress$okhttp", "()Lokhttp3/Address;", "connectionShutdownCount", "", "nextRouteToTry", "Lokhttp3/Route;", "otherFailureCount", "refusedStreamCount", "routeSelection", "Lokhttp3/internal/connection/RouteSelector$Selection;", "routeSelector", "Lokhttp3/internal/connection/RouteSelector;", "find", "Lokhttp3/internal/http/ExchangeCodec;", "client", "Lokhttp3/OkHttpClient;", "chain", "Lokhttp3/internal/http/RealInterceptorChain;", "findConnection", "Lokhttp3/internal/connection/RealConnection;", "connectTimeout", "readTimeout", "writeTimeout", "pingIntervalMillis", "connectionRetryEnabled", "", "findHealthyConnection", "doExtensiveHealthChecks", "retryAfterFailure", "retryRoute", "sameHostAndPort", "url", "Lokhttp3/HttpUrl;", "trackFailure", "", "e", "Ljava/io/IOException;", "okhttp"})
/*     */ public final class ExchangeFinder
/*     */ {
/*     */   private RouteSelector.Selection routeSelection;
/*     */   private RouteSelector routeSelector;
/*     */   private int refusedStreamCount;
/*     */   private int connectionShutdownCount;
/*     */   private int otherFailureCount;
/*     */   private Route nextRouteToTry;
/*     */   private final RealConnectionPool connectionPool;
/*     */   @NotNull
/*     */   private final Address address;
/*     */   private final RealCall call;
/*     */   private final EventListener eventListener;
/*     */   
/*     */   public ExchangeFinder(@NotNull RealConnectionPool connectionPool, @NotNull Address address, @NotNull RealCall call, @NotNull EventListener eventListener) {
/*  56 */     this.connectionPool = connectionPool; this.address = address; this.call = call; this.eventListener = eventListener; } @NotNull
/*     */   public final Address getAddress$okhttp() {
/*  58 */     return this.address;
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
/*     */   @NotNull
/*     */   public final ExchangeCodec find(@NotNull OkHttpClient client, @NotNull RealInterceptorChain chain) {
/*  73 */     Intrinsics.checkNotNullParameter(client, "client"); Intrinsics.checkNotNullParameter(chain, "chain"); try {
/*  74 */       RealConnection resultConnection = findHealthyConnection(
/*  75 */           chain.getConnectTimeoutMillis$okhttp(), 
/*  76 */           chain.getReadTimeoutMillis$okhttp(), 
/*  77 */           chain.getWriteTimeoutMillis$okhttp(), 
/*  78 */           client.pingIntervalMillis(), 
/*  79 */           client.retryOnConnectionFailure(), 
/*  80 */           Intrinsics.areEqual(chain.getRequest$okhttp().method(), "GET") ^ true);
/*     */       
/*  82 */       return resultConnection.newCodec$okhttp(client, chain);
/*  83 */     } catch (RouteException e) {
/*  84 */       trackFailure(e.getLastConnectException());
/*  85 */       throw (Throwable)e;
/*  86 */     } catch (IOException e) {
/*  87 */       trackFailure(e);
/*  88 */       throw (Throwable)new RouteException(e);
/*     */     } 
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
/*     */   private final RealConnection findHealthyConnection(int connectTimeout, int readTimeout, int writeTimeout, int pingIntervalMillis, boolean connectionRetryEnabled, boolean doExtensiveHealthChecks) throws IOException {
/*     */     while (true) {
/* 106 */       RealConnection candidate = findConnection(
/* 107 */           connectTimeout, 
/* 108 */           readTimeout, 
/* 109 */           writeTimeout, 
/* 110 */           pingIntervalMillis, 
/* 111 */           connectionRetryEnabled);
/*     */ 
/*     */ 
/*     */       
/* 115 */       if (candidate.isHealthy(doExtensiveHealthChecks)) {
/* 116 */         return candidate;
/*     */       }
/*     */ 
/*     */       
/* 120 */       candidate.noNewExchanges$okhttp();
/*     */ 
/*     */ 
/*     */       
/* 124 */       if (this.nextRouteToTry != null)
/*     */         continue; 
/* 126 */       boolean routesLeft = (this.routeSelection != null) ? this.routeSelection.hasNext() : true;
/* 127 */       if (routesLeft)
/*     */         continue; 
/* 129 */       boolean routesSelectionLeft = (this.routeSelector != null) ? this.routeSelector.hasNext() : true;
/* 130 */       if (routesSelectionLeft)
/*     */         continue;  break;
/* 132 */     }  throw (Throwable)new IOException("exhausted all routes");
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
/*     */   private final RealConnection findConnection(int connectTimeout, int readTimeout, int writeTimeout, int pingIntervalMillis, boolean connectionRetryEnabled) throws IOException {
/* 150 */     if (this.call.isCanceled()) throw (Throwable)new IOException("Canceled");
/*     */ 
/*     */     
/* 153 */     RealConnection callConnection = this.call.getConnection();
/* 154 */     if (callConnection != null) {
/* 155 */       Object toClose = null;
/* 156 */       boolean bool1 = false; synchronized (false) { int $i$a$-synchronized-ExchangeFinder$findConnection$1 = 0;
/* 157 */         if (callConnection.getNoNewExchanges() || !sameHostAndPort(callConnection.route().address().url())) {
/* 158 */           toClose = this.call.releaseConnectionNoEvents$okhttp();
/*     */         }
/* 160 */         Unit unit = Unit.INSTANCE;
/*     */         
/*     */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealConnection}, name=callConnection} */ }
/*     */       
/* 164 */       if (this.call.getConnection() != null) {
/* 165 */         bool1 = (toClose == null) ? true : false; boolean bool2 = false, bool3 = false; bool3 = false; boolean bool4 = false; if (!bool1) { boolean bool5 = false; String str = "Check failed."; throw (Throwable)new IllegalStateException(str.toString()); }
/* 166 */          return callConnection;
/*     */       } 
/*     */ 
/*     */       
/* 170 */       if (toClose != null) { Util.closeQuietly((Socket)toClose); } else {  }
/* 171 */        this.eventListener.connectionReleased(this.call, callConnection);
/*     */     } 
/*     */ 
/*     */     
/* 175 */     this.refusedStreamCount = 0;
/* 176 */     this.connectionShutdownCount = 0;
/* 177 */     this.otherFailureCount = 0;
/*     */ 
/*     */     
/* 180 */     if (this.connectionPool.callAcquirePooledConnection(this.address, this.call, null, false)) {
/* 181 */       Intrinsics.checkNotNull(this.call.getConnection()); RealConnection result = this.call.getConnection();
/* 182 */       this.eventListener.connectionAcquired(this.call, result);
/* 183 */       return result;
/*     */     } 
/*     */ 
/*     */     
/* 187 */     List<Route> routes = null;
/* 188 */     Route route = null;
/* 189 */     if (this.nextRouteToTry != null)
/*     */     
/* 191 */     { routes = (List)null;
/* 192 */       Intrinsics.checkNotNull(this.nextRouteToTry); route = this.nextRouteToTry;
/* 193 */       this.nextRouteToTry = (Route)null; }
/* 194 */     else { Intrinsics.checkNotNull(this.routeSelection); if (this.routeSelection != null && this.routeSelection.hasNext()) {
/*     */         
/* 196 */         routes = (List)null;
/* 197 */         Intrinsics.checkNotNull(this.routeSelection); route = this.routeSelection.next();
/*     */       } else {
/*     */         
/* 200 */         RouteSelector localRouteSelector = this.routeSelector;
/* 201 */         if (localRouteSelector == null) {
/* 202 */           localRouteSelector = new RouteSelector(this.address, this.call.getClient().getRouteDatabase(), this.call, this.eventListener);
/* 203 */           this.routeSelector = localRouteSelector;
/*     */         } 
/* 205 */         RouteSelector.Selection localRouteSelection = localRouteSelector.next();
/* 206 */         this.routeSelection = localRouteSelection;
/* 207 */         routes = localRouteSelection.getRoutes();
/*     */         
/* 209 */         if (this.call.isCanceled()) throw (Throwable)new IOException("Canceled");
/*     */ 
/*     */ 
/*     */         
/* 213 */         if (this.connectionPool.callAcquirePooledConnection(this.address, this.call, routes, false)) {
/* 214 */           Intrinsics.checkNotNull(this.call.getConnection()); RealConnection result = this.call.getConnection();
/* 215 */           this.eventListener.connectionAcquired(this.call, result);
/* 216 */           return result;
/*     */         } 
/*     */         
/* 219 */         route = localRouteSelection.next();
/*     */       }  }
/*     */ 
/*     */     
/* 223 */     RealConnection newConnection = new RealConnection(this.connectionPool, route);
/* 224 */     this.call.setConnectionToCancel(newConnection);
/*     */     try {
/* 226 */       newConnection.connect(
/* 227 */           connectTimeout, 
/* 228 */           readTimeout, 
/* 229 */           writeTimeout, 
/* 230 */           pingIntervalMillis, 
/* 231 */           connectionRetryEnabled, 
/* 232 */           this.call, 
/* 233 */           this.eventListener);
/*     */     } finally {
/*     */       
/* 236 */       this.call.setConnectionToCancel((RealConnection)null);
/*     */     } 
/* 238 */     this.call.getClient().getRouteDatabase().connected(newConnection.route());
/*     */ 
/*     */ 
/*     */     
/* 242 */     if (this.connectionPool.callAcquirePooledConnection(this.address, this.call, routes, true)) {
/* 243 */       Intrinsics.checkNotNull(this.call.getConnection()); RealConnection result = this.call.getConnection();
/* 244 */       this.nextRouteToTry = route;
/* 245 */       Util.closeQuietly(newConnection.socket());
/* 246 */       this.eventListener.connectionAcquired(this.call, result);
/* 247 */       return result;
/*     */     } 
/*     */     
/* 250 */     boolean bool = false; synchronized (false) { int $i$a$-synchronized-ExchangeFinder$findConnection$2 = 0;
/* 251 */       this.connectionPool.put(newConnection);
/* 252 */       this.call.acquireConnectionNoEvents(newConnection);
/* 253 */       Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealConnection}, name=newConnection} */ }
/*     */     
/* 255 */     this.eventListener.connectionAcquired(this.call, newConnection);
/* 256 */     return newConnection;
/*     */   }
/*     */   
/*     */   public final void trackFailure(@NotNull IOException e) {
/* 260 */     Intrinsics.checkNotNullParameter(e, "e"); this.nextRouteToTry = (Route)null;
/* 261 */     if (e instanceof StreamResetException && ((StreamResetException)e).errorCode == ErrorCode.REFUSED_STREAM) {
/* 262 */       int i; this.refusedStreamCount = (i = this.refusedStreamCount) + 1;
/* 263 */     } else if (e instanceof okhttp3.internal.http2.ConnectionShutdownException) {
/* 264 */       int i; this.connectionShutdownCount = (i = this.connectionShutdownCount) + 1;
/*     */     } else {
/* 266 */       int i; this.otherFailureCount = (i = this.otherFailureCount) + 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean retryAfterFailure() {
/* 275 */     if (this.refusedStreamCount == 0 && this.connectionShutdownCount == 0 && this.otherFailureCount == 0) {
/* 276 */       return false;
/*     */     }
/*     */     
/* 279 */     if (this.nextRouteToTry != null) {
/* 280 */       return true;
/*     */     }
/*     */     
/* 283 */     Route retryRoute = retryRoute();
/* 284 */     if (retryRoute != null) {
/*     */       
/* 286 */       this.nextRouteToTry = retryRoute;
/* 287 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 291 */     if (this.routeSelection != null) { if (this.routeSelection.hasNext() == true) return true;  }
/*     */     else
/*     */     {  }
/* 294 */      if (this.routeSelector != null) { RouteSelector localRouteSelector = this.routeSelector;
/*     */ 
/*     */       
/* 297 */       return localRouteSelector.hasNext(); }
/*     */     
/*     */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Route retryRoute() {
/* 306 */     if (this.refusedStreamCount > 1 || this.connectionShutdownCount > 1 || this.otherFailureCount > 0) {
/* 307 */       return null;
/*     */     }
/*     */     
/* 310 */     if (this.call.getConnection() != null) { RealConnection connection = this.call.getConnection();
/*     */       
/* 312 */       boolean bool = false; synchronized (false) { int $i$a$-synchronized-ExchangeFinder$retryRoute$1 = 0;
/* 313 */         if (connection.getRouteFailureCount$okhttp() != 0) { Route route1 = null; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealConnection}, name=connection} */ return route1; }
/* 314 */          if (!Util.canReuseConnectionFor(connection.route().address().url(), this.address.url())) { Route route1 = null; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealConnection}, name=connection} */ return route1; }
/* 315 */          Route route = connection.route(); /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealConnection}, name=connection} */ return route; }
/*     */        }
/*     */     
/*     */     this.call.getConnection();
/*     */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean sameHostAndPort(@NotNull HttpUrl url) {
/* 325 */     Intrinsics.checkNotNullParameter(url, "url"); HttpUrl routeUrl = this.address.url();
/* 326 */     return (url.port() == routeUrl.port() && Intrinsics.areEqual(url.host(), routeUrl.host()));
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/connection/ExchangeFinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */