/*    */ package okhttp3.internal.connection;
/*    */ 
/*    */ import java.util.LinkedHashSet;
/*    */ import java.util.Set;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import okhttp3.Route;
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
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000$\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020#\n\002\030\002\n\000\n\002\020\002\n\002\b\004\n\002\020\013\n\000\030\0002\0020\001B\005¢\006\002\020\002J\016\020\006\032\0020\0072\006\020\b\032\0020\005J\016\020\t\032\0020\0072\006\020\n\032\0020\005J\016\020\013\032\0020\f2\006\020\b\032\0020\005R\024\020\003\032\b\022\004\022\0020\0050\004X\004¢\006\002\n\000¨\006\r"}, d2 = {"Lokhttp3/internal/connection/RouteDatabase;", "", "()V", "failedRoutes", "", "Lokhttp3/Route;", "connected", "", "route", "failed", "failedRoute", "shouldPostpone", "", "okhttp"})
/*    */ public final class RouteDatabase
/*    */ {
/*    */   private final Set<Route> failedRoutes;
/*    */   
/*    */   public RouteDatabase() {
/* 27 */     boolean bool = false; this.failedRoutes = new LinkedHashSet<>();
/*    */   }
/*    */   
/*    */   public final synchronized void failed(@NotNull Route failedRoute) {
/* 31 */     Intrinsics.checkNotNullParameter(failedRoute, "failedRoute"); this.failedRoutes.add(failedRoute);
/*    */   }
/*    */ 
/*    */   
/*    */   public final synchronized void connected(@NotNull Route route) {
/* 36 */     Intrinsics.checkNotNullParameter(route, "route"); this.failedRoutes.remove(route);
/*    */   }
/*    */   
/*    */   public final synchronized boolean shouldPostpone(@NotNull Route route) {
/* 40 */     Intrinsics.checkNotNullParameter(route, "route"); return this.failedRoutes.contains(route);
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/connection/RouteDatabase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */