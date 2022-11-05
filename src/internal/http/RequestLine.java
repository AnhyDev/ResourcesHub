/*    */ package okhttp3.internal.http;
/*    */ 
/*    */ import java.net.Proxy;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import okhttp3.HttpUrl;
/*    */ import okhttp3.Request;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000,\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\000\bÆ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J\026\020\003\032\0020\0042\006\020\005\032\0020\0062\006\020\007\032\0020\bJ\030\020\t\032\0020\n2\006\020\005\032\0020\0062\006\020\007\032\0020\bH\002J\016\020\013\032\0020\0042\006\020\f\032\0020\r¨\006\016"}, d2 = {"Lokhttp3/internal/http/RequestLine;", "", "()V", "get", "", "request", "Lokhttp3/Request;", "proxyType", "Ljava/net/Proxy$Type;", "includeAuthorityInRequestLine", "", "requestPath", "url", "Lokhttp3/HttpUrl;", "okhttp"})
/*    */ public final class RequestLine
/*    */ {
/*    */   public static final RequestLine INSTANCE;
/*    */   
/*    */   static {
/* 23 */     RequestLine requestLine = new RequestLine();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public final String get(@NotNull Request request, @NotNull Proxy.Type proxyType) {
/* 30 */     Intrinsics.checkNotNullParameter(request, "request"); Intrinsics.checkNotNullParameter(proxyType, "proxyType"); boolean bool1 = false, bool2 = false; StringBuilder stringBuilder1 = new StringBuilder(); boolean bool3 = false, bool4 = false; StringBuilder $this$buildString = stringBuilder1; int $i$a$-buildString-RequestLine$get$1 = 0;
/* 31 */     $this$buildString.append(request.method());
/* 32 */     $this$buildString.append(' ');
/* 33 */     if (INSTANCE.includeAuthorityInRequestLine(request, proxyType)) {
/* 34 */       $this$buildString.append(request.url());
/*    */     } else {
/* 36 */       $this$buildString.append(INSTANCE.requestPath(request.url()));
/*    */     } 
/* 38 */     $this$buildString.append(" HTTP/1.1");
/*    */     Intrinsics.checkNotNullExpressionValue(stringBuilder1.toString(), "StringBuilder().apply(builderAction).toString()");
/*    */     return stringBuilder1.toString();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private final boolean includeAuthorityInRequestLine(Request request, Proxy.Type proxyType) {
/* 46 */     return (!request.isHttps() && proxyType == Proxy.Type.HTTP);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public final String requestPath(@NotNull HttpUrl url) {
/* 54 */     Intrinsics.checkNotNullParameter(url, "url"); String path = url.encodedPath();
/* 55 */     String query = url.encodedQuery();
/* 56 */     return (query != null) ? (path + '?' + query) : path;
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http/RequestLine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */