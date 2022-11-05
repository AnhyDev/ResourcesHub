/*    */ package okhttp3;
/*    */ 
/*    */ import java.util.List;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.collections.CollectionsKt;
/*    */ import kotlin.jvm.JvmField;
/*    */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\"\n\002\030\002\n\002\020\000\n\000\n\002\020 \n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\002\b\003\bf\030\000 \n2\0020\001:\001\nJ\026\020\002\032\b\022\004\022\0020\0040\0032\006\020\005\032\0020\006H&J\036\020\007\032\0020\b2\006\020\005\032\0020\0062\f\020\t\032\b\022\004\022\0020\0040\003H&¨\006\013"}, d2 = {"Lokhttp3/CookieJar;", "", "loadForRequest", "", "Lokhttp3/Cookie;", "url", "Lokhttp3/HttpUrl;", "saveFromResponse", "", "cookies", "Companion", "okhttp"})
/*    */ public interface CookieJar
/*    */ {
/*    */   @JvmField
/*    */   @NotNull
/*    */   public static final CookieJar NO_COOKIES;
/*    */   
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\b\003\030\0002\0020\001:\001\005B\007\b\002¢\006\002\020\002R\026\020\003\032\0020\0048\006X\004ø\001\000¢\006\002\n\000¨\006\001\002\007\n\005\bF0\001¨\006\006"}, d2 = {"Lokhttp3/CookieJar$Companion;", "", "()V", "NO_COOKIES", "Lokhttp3/CookieJar;", "NoCookies", "okhttp"})
/*    */   public static final class Companion
/*    */   {
/*    */     private Companion() {}
/*    */     
/*    */     @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000$\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020 \n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\002\b\002\b\002\030\0002\0020\001B\005¢\006\002\020\002J\026\020\003\032\b\022\004\022\0020\0050\0042\006\020\006\032\0020\007H\026J\036\020\b\032\0020\t2\006\020\006\032\0020\0072\f\020\n\032\b\022\004\022\0020\0050\004H\026¨\006\013"}, d2 = {"Lokhttp3/CookieJar$Companion$NoCookies;", "Lokhttp3/CookieJar;", "()V", "loadForRequest", "", "Lokhttp3/Cookie;", "url", "Lokhttp3/HttpUrl;", "saveFromResponse", "", "cookies", "okhttp"})
/*    */     private static final class NoCookies
/*    */       implements CookieJar
/*    */     {
/*    */       public void saveFromResponse(@NotNull HttpUrl url, @NotNull List cookies) {
/* 57 */         Intrinsics.checkNotNullParameter(url, "url"); Intrinsics.checkNotNullParameter(cookies, "cookies");
/*    */       } @NotNull
/*    */       public List<Cookie> loadForRequest(@NotNull HttpUrl url) {
/* 60 */         Intrinsics.checkNotNullParameter(url, "url"); return CollectionsKt.emptyList();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static final Companion Companion = new Companion(null);
/*    */   
/*    */   void saveFromResponse(@NotNull HttpUrl paramHttpUrl, @NotNull List<Cookie> paramList);
/*    */   
/*    */   @NotNull
/*    */   List<Cookie> loadForRequest(@NotNull HttpUrl paramHttpUrl);
/*    */   
/*    */   static {
/*    */     NO_COOKIES = new Companion.NoCookies();
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/CookieJar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */