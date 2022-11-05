/*     */ package okhttp3.internal.connection;
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.Proxy;
/*     */ import java.net.SocketAddress;
/*     */ import java.net.SocketException;
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.Address;
/*     */ import okhttp3.Call;
/*     */ import okhttp3.EventListener;
/*     */ import okhttp3.HttpUrl;
/*     */ import okhttp3.Route;
/*     */ import okhttp3.internal.Util;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000d\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020 \n\002\030\002\n\000\n\002\020\b\n\000\n\002\020!\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\003\n\002\030\002\n\002\b\003\030\000 !2\0020\001:\002!\"B%\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007\022\006\020\b\032\0020\t¢\006\002\020\nJ\t\020\025\032\0020\026H\002J\b\020\027\032\0020\026H\002J\t\020\030\032\0020\031H\002J\b\020\032\032\0020\024H\002J\020\020\033\032\0020\0342\006\020\035\032\0020\024H\002J\032\020\036\032\0020\0342\006\020\037\032\0020 2\b\020\035\032\004\030\0010\024H\002R\016\020\002\032\0020\003X\004¢\006\002\n\000R\016\020\006\032\0020\007X\004¢\006\002\n\000R\016\020\b\032\0020\tX\004¢\006\002\n\000R\024\020\013\032\b\022\004\022\0020\r0\fX\016¢\006\002\n\000R\016\020\016\032\0020\017X\016¢\006\002\n\000R\024\020\020\032\b\022\004\022\0020\0220\021X\004¢\006\002\n\000R\024\020\023\032\b\022\004\022\0020\0240\fX\016¢\006\002\n\000R\016\020\004\032\0020\005X\004¢\006\002\n\000¨\006#"}, d2 = {"Lokhttp3/internal/connection/RouteSelector;", "", "address", "Lokhttp3/Address;", "routeDatabase", "Lokhttp3/internal/connection/RouteDatabase;", "call", "Lokhttp3/Call;", "eventListener", "Lokhttp3/EventListener;", "(Lokhttp3/Address;Lokhttp3/internal/connection/RouteDatabase;Lokhttp3/Call;Lokhttp3/EventListener;)V", "inetSocketAddresses", "", "Ljava/net/InetSocketAddress;", "nextProxyIndex", "", "postponedRoutes", "", "Lokhttp3/Route;", "proxies", "Ljava/net/Proxy;", "hasNext", "", "hasNextProxy", "next", "Lokhttp3/internal/connection/RouteSelector$Selection;", "nextProxy", "resetNextInetSocketAddress", "", "proxy", "resetNextProxy", "url", "Lokhttp3/HttpUrl;", "Companion", "Selection", "okhttp"})
/*     */ public final class RouteSelector {
/*     */   private List<? extends Proxy> proxies;
/*     */   private int nextProxyIndex;
/*     */   private List<? extends InetSocketAddress> inetSocketAddresses;
/*     */   private final List<Route> postponedRoutes;
/*     */   private final Address address;
/*     */   private final RouteDatabase routeDatabase;
/*     */   private final Call call;
/*     */   private final EventListener eventListener;
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   public RouteSelector(@NotNull Address address, @NotNull RouteDatabase routeDatabase, @NotNull Call call, @NotNull EventListener eventListener) {
/*  36 */     this.address = address; this.routeDatabase = routeDatabase; this.call = call; this.eventListener = eventListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  43 */     this.proxies = CollectionsKt.emptyList();
/*     */ 
/*     */ 
/*     */     
/*  47 */     this.inetSocketAddresses = CollectionsKt.emptyList();
/*     */ 
/*     */     
/*  50 */     boolean bool = false; this.postponedRoutes = new ArrayList<>();
/*     */ 
/*     */     
/*  53 */     resetNextProxy(this.address.url(), this.address.proxy());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 3, d1 = {"\000\f\n\000\n\002\020 \n\002\030\002\n\000\020\000\032\b\022\004\022\0020\0020\001H\n¢\006\002\b\003"}, d2 = {"selectProxies", "", "Ljava/net/Proxy;", "invoke"})
/*     */   static final class RouteSelector$resetNextProxy$1
/*     */     extends Lambda
/*     */     implements Function0<List<? extends Proxy>>
/*     */   {
/*     */     @NotNull
/*     */     public final List<Proxy> invoke() {
/*  99 */       if (this.$proxy != null) return CollectionsKt.listOf(this.$proxy);
/*     */ 
/*     */       
/* 102 */       URI uri = this.$url.uri();
/* 103 */       if (uri.getHost() == null) return Util.immutableListOf((Object[])new Proxy[] { Proxy.NO_PROXY });
/*     */ 
/*     */       
/* 106 */       List<Proxy> proxiesOrNull = RouteSelector.this.address.proxySelector().select(uri);
/* 107 */       List<Proxy> list1 = proxiesOrNull; boolean bool1 = false, bool2 = false; if ((list1 == null || list1.isEmpty())) return Util.immutableListOf((Object[])new Proxy[] { Proxy.NO_PROXY });
/*     */       
/* 109 */       return Util.toImmutableList(proxiesOrNull);
/*     */     } RouteSelector$resetNextProxy$1(Proxy param1Proxy, HttpUrl param1HttpUrl) { super(0); }
/*     */   } public final boolean hasNext() { if (!hasNextProxy()) { List<Route> list = this.postponedRoutes; boolean bool = false; if (!list.isEmpty()); return false; }
/* 112 */      } private final void resetNextProxy(HttpUrl url, Proxy proxy) { RouteSelector$resetNextProxy$1 $fun$selectProxies$1 = new RouteSelector$resetNextProxy$1(proxy, url); this.eventListener.proxySelectStart(this.call, url);
/* 113 */     this.proxies = $fun$selectProxies$1.invoke();
/* 114 */     this.nextProxyIndex = 0;
/* 115 */     this.eventListener.proxySelectEnd(this.call, url, this.proxies); }
/*     */   @NotNull public final Selection next() throws IOException { if (!hasNext())
/*     */       throw (Throwable)new NoSuchElementException();  boolean bool = false; List<Route> routes = new ArrayList(); while (hasNextProxy()) { Proxy proxy = nextProxy(); for (InetSocketAddress inetSocketAddress : this.inetSocketAddresses) { Route route = new Route(this.address, proxy, inetSocketAddress); if (this.routeDatabase.shouldPostpone(route)) { List<Route> list2 = this.postponedRoutes; boolean bool3 = false; list2.add(route); continue; }  List<Route> list1 = routes; boolean bool2 = false; list1.add(route); }  List<Route> list = routes; boolean bool1 = false; if (!list.isEmpty())
/*     */         break;  }  if (routes.isEmpty()) { List<Route> list1 = routes, list2 = this.postponedRoutes; boolean bool1 = false; CollectionsKt.addAll(list1, list2); this.postponedRoutes.clear(); }
/* 119 */      return new Selection(routes); } private final boolean hasNextProxy() { return (this.nextProxyIndex < this.proxies.size()); }
/*     */ 
/*     */ 
/*     */   
/*     */   private final Proxy nextProxy() throws IOException {
/* 124 */     if (!hasNextProxy())
/* 125 */       throw (Throwable)new SocketException(
/* 126 */           "No route to " + this.address.url().host() + "; exhausted proxy configurations: " + this.proxies); 
/*     */     int i;
/* 128 */     this.nextProxyIndex = (i = this.nextProxyIndex) + 1; Proxy result = this.proxies.get(i);
/* 129 */     resetNextInetSocketAddress(result);
/* 130 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final void resetNextInetSocketAddress(Proxy proxy) throws IOException
/*     */   {
/* 137 */     boolean bool = false; List<? extends InetSocketAddress> mutableInetSocketAddresses = new ArrayList();
/* 138 */     this.inetSocketAddresses = mutableInetSocketAddresses;
/*     */     
/* 140 */     String socketHost = null;
/* 141 */     int socketPort = 0;
/* 142 */     if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
/* 143 */       socketHost = this.address.url().host();
/* 144 */       socketPort = this.address.url().port();
/*     */     } else {
/* 146 */       SocketAddress proxyAddress = proxy.address();
/* 147 */       boolean bool1 = proxyAddress instanceof InetSocketAddress; boolean bool2 = false, bool3 = false; if (!bool1) { int $i$a$-require-RouteSelector$resetNextInetSocketAddress$1 = 0; String str = 
/* 148 */           "Proxy.address() is not an InetSocketAddress: " + proxyAddress.getClass(); throw (Throwable)new IllegalArgumentException(str.toString()); }
/*     */       
/* 150 */       socketHost = Companion.getSocketHost((InetSocketAddress)proxyAddress);
/* 151 */       socketPort = ((InetSocketAddress)proxyAddress).getPort();
/*     */     } 
/*     */     
/* 154 */     int i = socketPort; if (1 <= i) { if (65535 >= i) {
/*     */ 
/*     */ 
/*     */         
/* 158 */         if (proxy.type() == Proxy.Type.SOCKS) {
/* 159 */           List<? extends InetSocketAddress> list = mutableInetSocketAddresses; InetSocketAddress inetSocketAddress = InetSocketAddress.createUnresolved(socketHost, socketPort); boolean bool1 = false; list.add(inetSocketAddress);
/*     */         } else {
/* 161 */           this.eventListener.dnsStart(this.call, socketHost);
/*     */ 
/*     */           
/* 164 */           List addresses = this.address.dns().lookup(socketHost);
/* 165 */           if (addresses.isEmpty()) {
/* 166 */             throw (Throwable)new UnknownHostException(this.address.dns() + " returned no addresses for " + socketHost);
/*     */           }
/*     */           
/* 169 */           this.eventListener.dnsEnd(this.call, socketHost, addresses);
/*     */           
/* 171 */           for (InetAddress inetAddress : addresses) {
/* 172 */             List<? extends InetSocketAddress> list = mutableInetSocketAddresses; InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress, socketPort); boolean bool1 = false; list.add(inetSocketAddress);
/*     */           } 
/*     */         }  return;
/*     */       }  }
/*     */     else
/*     */     { 65535; }
/* 178 */      throw (Throwable)new SocketException("No route to " + socketHost + ':' + socketPort + "; port is out of range"); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000&\n\002\030\002\n\002\020\000\n\000\n\002\020 \n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\003\n\002\020\013\n\002\b\002\030\0002\0020\001B\023\022\f\020\002\032\b\022\004\022\0020\0040\003¢\006\002\020\005J\t\020\n\032\0020\013H\002J\t\020\f\032\0020\004H\002R\016\020\006\032\0020\007X\016¢\006\002\n\000R\027\020\002\032\b\022\004\022\0020\0040\003¢\006\b\n\000\032\004\b\b\020\t¨\006\r"}, d2 = {"Lokhttp3/internal/connection/RouteSelector$Selection;", "", "routes", "", "Lokhttp3/Route;", "(Ljava/util/List;)V", "nextRouteIndex", "", "getRoutes", "()Ljava/util/List;", "hasNext", "", "next", "okhttp"}) public static final class Selection { private int nextRouteIndex; @NotNull private final List<Route> routes; @NotNull public final List<Route> getRoutes() { return this.routes; } public Selection(@NotNull List<Route> routes) { this.routes = routes; }
/*     */     
/*     */     public final boolean hasNext() {
/* 181 */       return (this.nextRouteIndex < this.routes.size());
/*     */     } @NotNull
/*     */     public final Route next() {
/* 184 */       if (!hasNext()) throw (Throwable)new NoSuchElementException();  int i;
/* 185 */       this.nextRouteIndex = (i = this.nextRouteIndex) + 1; return this.routes.get(i);
/*     */     } }
/*     */ 
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\030\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\025\020\003\032\0020\004*\0020\0058F¢\006\006\032\004\b\006\020\007¨\006\b"}, d2 = {"Lokhttp3/internal/connection/RouteSelector$Companion;", "", "()V", "socketHost", "", "Ljava/net/InetSocketAddress;", "getSocketHost", "(Ljava/net/InetSocketAddress;)Ljava/lang/String;", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */     
/*     */     @NotNull
/*     */     public final String getSocketHost(@NotNull InetSocketAddress $this$socketHost) {
/* 195 */       Intrinsics.checkNotNullParameter($this$socketHost, "$this$socketHost"); if ($this$socketHost.getAddress() != null) { InetAddress address = $this$socketHost.getAddress();
/*     */ 
/*     */ 
/*     */         
/* 199 */         Intrinsics.checkNotNullExpressionValue(address.getHostAddress(), "address.hostAddress"); return address.getHostAddress(); }
/*     */       
/*     */       $this$socketHost.getAddress();
/*     */       Intrinsics.checkNotNullExpressionValue($this$socketHost.getHostName(), "hostName");
/*     */       return $this$socketHost.getHostName();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/connection/RouteSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */