/*     */ package okhttp3.internal.connection;
/*     */ 
/*     */ import java.lang.ref.Reference;
/*     */ import java.net.Socket;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.Unit;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.Address;
/*     */ import okhttp3.ConnectionPool;
/*     */ import okhttp3.Route;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.concurrent.Task;
/*     */ import okhttp3.internal.concurrent.TaskQueue;
/*     */ import okhttp3.internal.concurrent.TaskRunner;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000c\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\020\b\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\b\003\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\013\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\002\b\007\n\002\020\002\n\002\b\005*\001\016\030\000 (2\0020\001:\001(B%\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007\022\006\020\b\032\0020\t¢\006\002\020\nJ.\020\024\032\0020\0252\006\020\026\032\0020\0272\006\020\030\032\0020\0312\016\020\032\032\n\022\004\022\0020\034\030\0010\0332\006\020\035\032\0020\025J\016\020\036\032\0020\0072\006\020\037\032\0020\007J\016\020 \032\0020\0252\006\020!\032\0020\022J\006\020\"\032\0020\005J\006\020#\032\0020$J\006\020%\032\0020\005J\030\020&\032\0020\0052\006\020!\032\0020\0222\006\020\037\032\0020\007H\002J\016\020'\032\0020$2\006\020!\032\0020\022R\016\020\013\032\0020\fX\004¢\006\002\n\000R\020\020\r\032\0020\016X\004¢\006\004\n\002\020\017R\024\020\020\032\b\022\004\022\0020\0220\021X\004¢\006\002\n\000R\016\020\023\032\0020\007X\004¢\006\002\n\000R\016\020\004\032\0020\005X\004¢\006\002\n\000¨\006)"}, d2 = {"Lokhttp3/internal/connection/RealConnectionPool;", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "maxIdleConnections", "", "keepAliveDuration", "", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "(Lokhttp3/internal/concurrent/TaskRunner;IJLjava/util/concurrent/TimeUnit;)V", "cleanupQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "cleanupTask", "okhttp3/internal/connection/RealConnectionPool$cleanupTask$1", "Lokhttp3/internal/connection/RealConnectionPool$cleanupTask$1;", "connections", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lokhttp3/internal/connection/RealConnection;", "keepAliveDurationNs", "callAcquirePooledConnection", "", "address", "Lokhttp3/Address;", "call", "Lokhttp3/internal/connection/RealCall;", "routes", "", "Lokhttp3/Route;", "requireMultiplexed", "cleanup", "now", "connectionBecameIdle", "connection", "connectionCount", "evictAll", "", "idleConnectionCount", "pruneAndGetAllocationCount", "put", "Companion", "okhttp"})
/*     */ public final class RealConnectionPool {
/*     */   private final long keepAliveDurationNs;
/*     */   private final TaskQueue cleanupQueue;
/*     */   private final RealConnectionPool$cleanupTask$1 cleanupTask;
/*     */   private final ConcurrentLinkedQueue<RealConnection> connections;
/*     */   private final int maxIdleConnections;
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*  33 */   public RealConnectionPool(@NotNull TaskRunner taskRunner, int maxIdleConnections, long keepAliveDuration, @NotNull TimeUnit timeUnit) { this.maxIdleConnections = maxIdleConnections;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  40 */     this.keepAliveDurationNs = timeUnit.toNanos(keepAliveDuration);
/*     */     
/*  42 */     this.cleanupQueue = taskRunner.newQueue();
/*  43 */     this.cleanupTask = new RealConnectionPool$cleanupTask$1(Util.okHttpName + " ConnectionPool");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  52 */     this.connections = new ConcurrentLinkedQueue<>();
/*     */ 
/*     */ 
/*     */     
/*  56 */     boolean bool1 = (keepAliveDuration > 0L) ? true : false, bool2 = false, bool3 = false; if (!bool1)
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
/* 254 */       int $i$a$-require-RealConnectionPool$1 = 0; String str = "keepAliveDuration <= 0: " + keepAliveDuration; throw (Throwable)new IllegalArgumentException(str.toString()); }  } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\021\n\000\n\002\030\002\n\000\n\002\020\t\n\000*\001\000\b\n\030\0002\0020\001J\b\020\002\032\0020\003H\026¨\006\004"}, d2 = {"okhttp3/internal/connection/RealConnectionPool$cleanupTask$1", "Lokhttp3/internal/concurrent/Task;", "runOnce", "", "okhttp"}) public static final class RealConnectionPool$cleanupTask$1 extends Task {
/* 255 */     RealConnectionPool$cleanupTask$1(String $super_call_param$1) { super($super_call_param$1, false, 2, null); } public long runOnce() { return RealConnectionPool.this.cleanup(System.nanoTime()); } } public final int idleConnectionCount() { Iterable<RealConnection> $this$count$iv = this.connections; int $i$f$count = 0; int count$iv = 0; for (RealConnection element$iv : $this$count$iv) { RealConnection it = element$iv; int $i$a$-count-RealConnectionPool$idleConnectionCount$1 = 0; Intrinsics.checkNotNullExpressionValue(it, "it"); RealConnection realConnection1 = it; boolean bool1 = false, bool2 = false; /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealConnection}, name=null} */ }  return ($this$count$iv instanceof Collection && ((Collection)$this$count$iv).isEmpty()) ? 0 : count$iv; }
/* 256 */   public final int connectionCount() { return this.connections.size(); } public final void put(@NotNull RealConnection connection) { Intrinsics.checkNotNullParameter(connection, "connection"); Object $this$assertThreadHoldsLock$iv = connection; int $i$f$assertThreadHoldsLock = 0; if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv))
/* 257 */     { Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); }  this.connections.add(connection); TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null); }
/*     */   public final boolean callAcquirePooledConnection(@NotNull Address address, @NotNull RealCall call, @Nullable List<Route> routes, boolean requireMultiplexed) { Intrinsics.checkNotNullParameter(address, "address"); Intrinsics.checkNotNullParameter(call, "call"); for (RealConnection connection : this.connections) { Intrinsics.checkNotNullExpressionValue(connection, "connection"); RealConnection realConnection1 = connection; boolean bool = false; synchronized (false) { int $i$a$-synchronized-RealConnectionPool$callAcquirePooledConnection$1 = 0; if ((requireMultiplexed && !connection.isMultiplexed$okhttp()) || !connection.isEligible$okhttp(address, routes)) { Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealConnection}, name=null} */ }  call.acquireConnectionNoEvents(connection); boolean bool1 = true; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealConnection}, name=null} */ return bool1; }
/*     */        }
/* 260 */      return false; } public final boolean connectionBecameIdle(@NotNull RealConnection connection) { Intrinsics.checkNotNullParameter(connection, "connection"); Object $this$assertThreadHoldsLock$iv = connection; int $i$f$assertThreadHoldsLock = 0; if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
/* 261 */       Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv);
/*     */     }  connection.setNoNewExchanges(true); this.connections.remove(connection); if (this.connections.isEmpty())
/*     */       this.cleanupQueue.cancelAll();  TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null); return (connection.getNoNewExchanges() || this.maxIdleConnections == 0); } private final int pruneAndGetAllocationCount(RealConnection connection, long now) { Object $this$assertThreadHoldsLock$iv = connection; int $i$f$assertThreadHoldsLock = 0;
/* 264 */     if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
/* 265 */       Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv);
/*     */     } 
/*     */     List<Reference<RealCall>> references = connection.getCalls();
/*     */     int i = 0;
/*     */     while (i < references.size()) {
/*     */       Reference reference = references.get(i);
/*     */       if (reference.get() != null) {
/*     */         i++;
/*     */         continue;
/*     */       } 
/*     */       if (reference == null)
/*     */         throw new NullPointerException("null cannot be cast to non-null type okhttp3.internal.connection.RealCall.CallReference"); 
/*     */       RealCall.CallReference callReference = (RealCall.CallReference)reference;
/*     */       String message = "A connection to " + connection.route().address().url() + " was leaked. " + "Did you forget to close a response body?";
/*     */       Platform.Companion.get().logCloseableLeak(message, callReference.getCallStackTrace());
/*     */       references.remove(i);
/*     */       connection.setNoNewExchanges(true);
/*     */       if (references.isEmpty()) {
/*     */         connection.setIdleAtNs$okhttp(now - this.keepAliveDurationNs);
/*     */         return 0;
/*     */       } 
/*     */     } 
/*     */     return references.size(); }
/*     */ 
/*     */   
/*     */   public final void evictAll() {
/*     */     Intrinsics.checkNotNullExpressionValue(this.connections.iterator(), "connections.iterator()");
/*     */     Iterator<RealConnection> i = this.connections.iterator();
/*     */     while (i.hasNext()) {
/*     */       RealConnection connection = i.next();
/*     */       Intrinsics.checkNotNullExpressionValue(connection, "connection");
/*     */       RealConnection realConnection1 = connection;
/*     */       boolean bool = false;
/*     */       synchronized (false) {
/*     */         int $i$a$-synchronized-RealConnectionPool$evictAll$socketToClose$1 = 0;
/*     */         i.remove();
/*     */         connection.setNoNewExchanges(true);
/*     */         Socket socket = connection.getCalls().isEmpty() ? connection.socket() : null;
/*     */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealConnection}, name=null} */
/*     */       } 
/*     */       Socket socketToClose = socket;
/*     */       if (socketToClose != null)
/*     */         Util.closeQuietly(socketToClose); 
/*     */     } 
/*     */     if (this.connections.isEmpty())
/*     */       this.cleanupQueue.cancelAll(); 
/*     */   }
/*     */   
/*     */   public final long cleanup(long now) {
/*     */     int inUseConnectionCount = 0;
/*     */     int idleConnectionCount = 0;
/*     */     Object longestIdleConnection = null;
/*     */     long longestIdleDurationNs = Long.MIN_VALUE;
/*     */     for (RealConnection connection : this.connections) {
/*     */       Intrinsics.checkNotNullExpressionValue(connection, "connection");
/*     */       RealConnection realConnection1 = connection;
/*     */       boolean bool = false;
/*     */       synchronized (false) {
/*     */         int $i$a$-synchronized-RealConnectionPool$cleanup$1 = 0;
/*     */         int i;
/*     */         inUseConnectionCount = (i = inUseConnectionCount) + 1;
/*     */         idleConnectionCount = (i = idleConnectionCount) + 1;
/*     */         long idleDurationNs = now - connection.getIdleAtNs$okhttp();
/*     */         longestIdleDurationNs = idleDurationNs;
/*     */         longestIdleConnection = connection;
/*     */         null = (pruneAndGetAllocationCount(connection, now) > 0) ? Integer.valueOf(i) : ((idleDurationNs > longestIdleDurationNs) ? Unit.INSTANCE : Unit.INSTANCE);
/*     */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/connection/RealConnection}, name=null} */
/*     */       } 
/*     */     } 
/*     */     if (longestIdleDurationNs >= this.keepAliveDurationNs || idleConnectionCount > this.maxIdleConnections) {
/*     */       Intrinsics.checkNotNull(longestIdleConnection);
/*     */       Object object = longestIdleConnection;
/*     */       boolean bool = false;
/*     */       synchronized (false) {
/*     */         int $i$a$-synchronized-RealConnectionPool$cleanup$2 = 0;
/*     */         List<Reference<RealCall>> list = object.getCalls();
/*     */         boolean bool1 = false;
/*     */         if (!list.isEmpty()) {
/*     */           long l = 0L;
/*     */           /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
/*     */           return l;
/*     */         } 
/*     */         if (object.getIdleAtNs$okhttp() + longestIdleDurationNs != now) {
/*     */           long l = 0L;
/*     */           /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
/*     */           return l;
/*     */         } 
/*     */         object.setNoNewExchanges(true);
/*     */         null = this.connections.remove(longestIdleConnection);
/*     */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
/*     */       } 
/*     */       Util.closeQuietly(object.socket());
/*     */       if (this.connections.isEmpty())
/*     */         this.cleanupQueue.cancelAll(); 
/*     */       return 0L;
/*     */     } 
/*     */     if (idleConnectionCount > 0)
/*     */       return this.keepAliveDurationNs - longestIdleDurationNs; 
/*     */     if (inUseConnectionCount > 0)
/*     */       return this.keepAliveDurationNs; 
/*     */     return -1L;
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\030\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\016\020\003\032\0020\0042\006\020\005\032\0020\006¨\006\007"}, d2 = {"Lokhttp3/internal/connection/RealConnectionPool$Companion;", "", "()V", "get", "Lokhttp3/internal/connection/RealConnectionPool;", "connectionPool", "Lokhttp3/ConnectionPool;", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */     
/*     */     @NotNull
/*     */     public final RealConnectionPool get(@NotNull ConnectionPool connectionPool) {
/*     */       Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
/*     */       return connectionPool.getDelegate$okhttp();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/connection/RealConnectionPool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */