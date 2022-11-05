/*    */ package okhttp3.internal.http;
/*    */ 
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.JvmStatic;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\013\n\000\n\002\020\016\n\002\b\005\bÆ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J\016\020\003\032\0020\0042\006\020\005\032\0020\006J\020\020\007\032\0020\0042\006\020\005\032\0020\006H\007J\016\020\b\032\0020\0042\006\020\005\032\0020\006J\016\020\t\032\0020\0042\006\020\005\032\0020\006J\020\020\n\032\0020\0042\006\020\005\032\0020\006H\007¨\006\013"}, d2 = {"Lokhttp3/internal/http/HttpMethod;", "", "()V", "invalidatesCache", "", "method", "", "permitsRequestBody", "redirectsToGet", "redirectsWithBody", "requiresRequestBody", "okhttp"})
/*    */ public final class HttpMethod
/*    */ {
/*    */   public static final HttpMethod INSTANCE;
/*    */   
/*    */   static {
/* 18 */     HttpMethod httpMethod = new HttpMethod(); } public final boolean invalidatesCache(@NotNull String method) {
/* 19 */     Intrinsics.checkNotNullParameter(method, "method");
/*    */ 
/*    */ 
/*    */     
/* 23 */     return (Intrinsics.areEqual(method, "POST") || Intrinsics.areEqual(method, "PATCH") || Intrinsics.areEqual(method, "PUT") || Intrinsics.areEqual(method, "DELETE") || Intrinsics.areEqual(method, "MOVE"));
/*    */   } @JvmStatic
/*    */   public static final boolean requiresRequestBody(@NotNull String method) {
/* 26 */     Intrinsics.checkNotNullParameter(method, "method");
/*    */ 
/*    */ 
/*    */     
/* 30 */     return (Intrinsics.areEqual(method, "POST") || Intrinsics.areEqual(method, "PUT") || Intrinsics.areEqual(method, "PATCH") || Intrinsics.areEqual(method, "PROPPATCH") || Intrinsics.areEqual(method, "REPORT"));
/*    */   } @JvmStatic
/*    */   public static final boolean permitsRequestBody(@NotNull String method) {
/* 33 */     Intrinsics.checkNotNullParameter(method, "method"); return (!Intrinsics.areEqual(method, "GET") && !Intrinsics.areEqual(method, "HEAD"));
/*    */   }
/*    */   
/*    */   public final boolean redirectsWithBody(@NotNull String method) {
/* 37 */     Intrinsics.checkNotNullParameter(method, "method"); return Intrinsics.areEqual(method, "PROPFIND");
/*    */   }
/*    */   
/*    */   public final boolean redirectsToGet(@NotNull String method) {
/* 41 */     Intrinsics.checkNotNullParameter(method, "method"); return Intrinsics.areEqual(method, "PROPFIND") ^ true;
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http/HttpMethod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */