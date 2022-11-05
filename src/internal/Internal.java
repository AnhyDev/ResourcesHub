/*    */ package okhttp3.internal;
/*    */ 
/*    */ import javax.net.ssl.SSLSocket;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.JvmName;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import okhttp3.Cache;
/*    */ import okhttp3.ConnectionSpec;
/*    */ import okhttp3.Cookie;
/*    */ import okhttp3.Headers;
/*    */ import okhttp3.HttpUrl;
/*    */ import okhttp3.Request;
/*    */ import okhttp3.Response;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 2, d1 = {"\000T\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\003\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\t\n\000\n\002\030\002\n\002\b\002\032\026\020\000\032\0020\0012\006\020\002\032\0020\0012\006\020\003\032\0020\004\032\036\020\000\032\0020\0012\006\020\002\032\0020\0012\006\020\005\032\0020\0042\006\020\006\032\0020\004\032\036\020\007\032\0020\b2\006\020\t\032\0020\n2\006\020\013\032\0020\f2\006\020\r\032\0020\016\032\030\020\017\032\004\030\0010\0202\006\020\021\032\0020\0222\006\020\023\032\0020\024\032\026\020\025\032\0020\0042\006\020\026\032\0020\0272\006\020\030\032\0020\016\032 \020\031\032\004\030\0010\0272\006\020\032\032\0020\0332\006\020\034\032\0020\0352\006\020\036\032\0020\004¨\006\037"}, d2 = {"addHeaderLenient", "Lokhttp3/Headers$Builder;", "builder", "line", "", "name", "value", "applyConnectionSpec", "", "connectionSpec", "Lokhttp3/ConnectionSpec;", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "isFallback", "", "cacheGet", "Lokhttp3/Response;", "cache", "Lokhttp3/Cache;", "request", "Lokhttp3/Request;", "cookieToString", "cookie", "Lokhttp3/Cookie;", "forObsoleteRfc2965", "parseCookie", "currentTimeMillis", "", "url", "Lokhttp3/HttpUrl;", "setCookie", "okhttp"})
/*    */ @JvmName(name = "Internal")
/*    */ public final class Internal
/*    */ {
/*    */   @Nullable
/*    */   public static final Cookie parseCookie(long currentTimeMillis, @NotNull HttpUrl url, @NotNull String setCookie) {
/* 31 */     Intrinsics.checkNotNullParameter(url, "url"); Intrinsics.checkNotNullParameter(setCookie, "setCookie"); return Cookie.Companion.parse$okhttp(currentTimeMillis, url, setCookie);
/*    */   } @NotNull
/*    */   public static final String cookieToString(@NotNull Cookie cookie, boolean forObsoleteRfc2965) {
/* 34 */     Intrinsics.checkNotNullParameter(cookie, "cookie"); return cookie.toString$okhttp(forObsoleteRfc2965);
/*    */   } @NotNull
/*    */   public static final Headers.Builder addHeaderLenient(@NotNull Headers.Builder builder, @NotNull String line) {
/* 37 */     Intrinsics.checkNotNullParameter(builder, "builder"); Intrinsics.checkNotNullParameter(line, "line"); return builder.addLenient$okhttp(line);
/*    */   }
/*    */   @NotNull
/* 40 */   public static final Headers.Builder addHeaderLenient(@NotNull Headers.Builder builder, @NotNull String name, @NotNull String value) { Intrinsics.checkNotNullParameter(builder, "builder"); Intrinsics.checkNotNullParameter(name, "name"); Intrinsics.checkNotNullParameter(value, "value"); return builder.addLenient$okhttp(name, value); } @Nullable
/*    */   public static final Response cacheGet(@NotNull Cache cache, @NotNull Request request) {
/* 42 */     Intrinsics.checkNotNullParameter(cache, "cache"); Intrinsics.checkNotNullParameter(request, "request"); return cache.get$okhttp(request);
/*    */   }
/*    */   public static final void applyConnectionSpec(@NotNull ConnectionSpec connectionSpec, @NotNull SSLSocket sslSocket, boolean isFallback) {
/* 45 */     Intrinsics.checkNotNullParameter(connectionSpec, "connectionSpec"); Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); connectionSpec.apply$okhttp(sslSocket, isFallback);
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/Internal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */