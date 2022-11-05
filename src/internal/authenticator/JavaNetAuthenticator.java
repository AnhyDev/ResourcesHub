/*    */ package okhttp3.internal.authenticator;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.Authenticator;
/*    */ import java.net.InetAddress;
/*    */ import java.net.InetSocketAddress;
/*    */ import java.net.PasswordAuthentication;
/*    */ import java.net.Proxy;
/*    */ import java.util.List;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import kotlin.text.StringsKt;
/*    */ import okhttp3.Authenticator;
/*    */ import okhttp3.Challenge;
/*    */ import okhttp3.Credentials;
/*    */ import okhttp3.Dns;
/*    */ import okhttp3.HttpUrl;
/*    */ import okhttp3.Request;
/*    */ import okhttp3.Response;
/*    */ import okhttp3.Route;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0006\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\030\0002\0020\001B\017\022\b\b\002\020\002\032\0020\003¢\006\002\020\004J\034\020\005\032\004\030\0010\0062\b\020\007\032\004\030\0010\b2\006\020\t\032\0020\nH\026J\034\020\013\032\0020\f*\0020\r2\006\020\016\032\0020\0172\006\020\020\032\0020\003H\002R\016\020\002\032\0020\003X\004¢\006\002\n\000¨\006\021"}, d2 = {"Lokhttp3/internal/authenticator/JavaNetAuthenticator;", "Lokhttp3/Authenticator;", "defaultDns", "Lokhttp3/Dns;", "(Lokhttp3/Dns;)V", "authenticate", "Lokhttp3/Request;", "route", "Lokhttp3/Route;", "response", "Lokhttp3/Response;", "connectToInetAddress", "Ljava/net/InetAddress;", "Ljava/net/Proxy;", "url", "Lokhttp3/HttpUrl;", "dns", "okhttp"})
/*    */ public final class JavaNetAuthenticator
/*    */   implements Authenticator
/*    */ {
/*    */   private final Dns defaultDns;
/*    */   
/*    */   public JavaNetAuthenticator(@NotNull Dns defaultDns) {
/* 34 */     this.defaultDns = defaultDns;
/*    */   } @Nullable
/*    */   public Request authenticate(@Nullable Route route, @NotNull Response response) throws IOException {
/* 37 */     Intrinsics.checkNotNullParameter(response, "response"); List challenges = response.challenges();
/* 38 */     Request request = response.request();
/* 39 */     HttpUrl url = request.url();
/* 40 */     boolean proxyAuthorization = (response.code() == 407);
/* 41 */     if (route == null || route.proxy() == null) route.proxy();  Proxy proxy = Proxy.NO_PROXY;
/*    */     
/* 43 */     for (Challenge challenge : challenges) {
/* 44 */       if (!StringsKt.equals("Basic", challenge.scheme(), true)) {
/*    */         continue;
/*    */       }
/*    */       
/* 48 */       if (route == null || route.address() == null || route.address().dns() == null) route.address().dns();  Dns dns = this.defaultDns;
/*    */       
/* 50 */       if (proxy.address() == null) throw new NullPointerException("null cannot be cast to non-null type java.net.InetSocketAddress");  InetSocketAddress proxyAddress = (InetSocketAddress)proxy.address();
/*    */ 
/*    */       
/* 53 */       Intrinsics.checkNotNullExpressionValue(proxy, "proxy");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 64 */       Intrinsics.checkNotNullExpressionValue(proxy, "proxy"); PasswordAuthentication auth = proxyAuthorization ? Authenticator.requestPasswordAuthentication(proxyAddress.getHostName(), connectToInetAddress(proxy, url, dns), proxyAddress.getPort(), url.scheme(), challenge.realm(), challenge.scheme(), url.url(), Authenticator.RequestorType.PROXY) : Authenticator.requestPasswordAuthentication(url.host(), connectToInetAddress(proxy, url, dns), 
/* 65 */           url.port(), 
/* 66 */           url.scheme(), 
/* 67 */           challenge.realm(), 
/* 68 */           challenge.scheme(), 
/* 69 */           url.url(), 
/* 70 */           Authenticator.RequestorType.SERVER);
/*    */ 
/*    */ 
/*    */       
/* 74 */       if (auth != null) {
/* 75 */         String credentialHeader = proxyAuthorization ? "Proxy-Authorization" : "Authorization";
/*    */         
/* 77 */         Intrinsics.checkNotNullExpressionValue(auth.getUserName(), "auth.userName"); Intrinsics.checkNotNullExpressionValue(auth.getPassword(), "auth.password"); char[] arrayOfChar = auth.getPassword(); boolean bool = false; String credential = Credentials.basic(auth.getUserName(), new String(arrayOfChar), challenge.charset());
/* 78 */         return request.newBuilder()
/* 79 */           .header(credentialHeader, credential)
/* 80 */           .build();
/*    */       } 
/*    */     } 
/*    */     
/* 84 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   private final InetAddress connectToInetAddress(Proxy $this$connectToInetAddress, HttpUrl url, Dns dns) throws IOException {
/* 89 */     if ($this$connectToInetAddress.type() == null) { $this$connectToInetAddress.type(); } else { switch (JavaNetAuthenticator$WhenMappings.$EnumSwitchMapping$0[$this$connectToInetAddress.type().ordinal()]) { case 1:
/*    */          }  }
/* 91 */      if ($this$connectToInetAddress.address() == null) throw new NullPointerException("null cannot be cast to non-null type java.net.InetSocketAddress");  Intrinsics.checkNotNullExpressionValue(((InetSocketAddress)$this$connectToInetAddress.address()).getAddress(), "(address() as InetSocketAddress).address"); return ((InetSocketAddress)$this$connectToInetAddress.address()).getAddress();
/*    */   }
/*    */   
/*    */   public JavaNetAuthenticator() {
/*    */     this(null, 1, null);
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/authenticator/JavaNetAuthenticator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */