/*    */ package okhttp3;
/*    */ 
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import kotlin.Metadata;
/*    */ import okhttp3.internal.concurrent.TaskRunner;
/*    */ import okhttp3.internal.connection.RealConnectionPool;
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
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000.\n\002\030\002\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\005\n\002\020\002\n\002\b\002\030\0002\0020\001B\037\b\026\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007¢\006\002\020\bB\007\b\026¢\006\002\020\tB\017\b\000\022\006\020\n\032\0020\013¢\006\002\020\fJ\006\020\017\032\0020\003J\006\020\020\032\0020\021J\006\020\022\032\0020\003R\024\020\n\032\0020\013X\004¢\006\b\n\000\032\004\b\r\020\016¨\006\023"}, d2 = {"Lokhttp3/ConnectionPool;", "", "maxIdleConnections", "", "keepAliveDuration", "", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "(IJLjava/util/concurrent/TimeUnit;)V", "()V", "delegate", "Lokhttp3/internal/connection/RealConnectionPool;", "(Lokhttp3/internal/connection/RealConnectionPool;)V", "getDelegate$okhttp", "()Lokhttp3/internal/connection/RealConnectionPool;", "connectionCount", "evictAll", "", "idleConnectionCount", "okhttp"})
/*    */ public final class ConnectionPool
/*    */ {
/*    */   @NotNull
/*    */   private final RealConnectionPool delegate;
/*    */   
/*    */   public ConnectionPool(@NotNull RealConnectionPool delegate) {
/* 33 */     this.delegate = delegate; } @NotNull
/* 34 */   public final RealConnectionPool getDelegate$okhttp() { return this.delegate; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ConnectionPool(int maxIdleConnections, long keepAliveDuration, @NotNull TimeUnit timeUnit) {
/* 40 */     this(new RealConnectionPool(
/* 41 */           TaskRunner.INSTANCE, 
/* 42 */           maxIdleConnections, 
/* 43 */           keepAliveDuration, 
/* 44 */           timeUnit));
/*    */   }
/*    */   public ConnectionPool() {
/* 47 */     this(5, 5L, TimeUnit.MINUTES);
/*    */   }
/*    */   public final int idleConnectionCount() {
/* 50 */     return this.delegate.idleConnectionCount();
/*    */   }
/*    */   public final int connectionCount() {
/* 53 */     return this.delegate.connectionCount();
/*    */   }
/*    */   
/*    */   public final void evictAll() {
/* 57 */     this.delegate.evictAll();
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/ConnectionPool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */