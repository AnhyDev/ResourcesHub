/*     */ package okhttp3;
/*     */ 
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Deque;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.SynchronousQueue;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import kotlin.Deprecated;
/*     */ import kotlin.DeprecationLevel;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.ReplaceWith;
/*     */ import kotlin.Unit;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.jvm.JvmName;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.connection.RealCall;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\\\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\006\n\002\020\b\n\002\b\b\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\b\n\002\020\016\n\002\b\003\n\002\030\002\n\002\b\003\n\002\020\013\n\000\n\002\020 \n\002\030\002\n\002\b\004\030\0002\0020\001B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B\005¢\006\002\020\005J\006\020\036\032\0020\037J\031\020 \032\0020\0372\n\020!\032\0060\032R\0020\033H\000¢\006\002\b\"J\025\020#\032\0020\0372\006\020!\032\0020\033H\000¢\006\002\b$J\r\020\002\032\0020\003H\007¢\006\002\b%J\026\020&\032\b\030\0010\032R\0020\0332\006\020'\032\0020(H\002J)\020)\032\0020\037\"\004\b\000\020*2\f\020+\032\b\022\004\022\002H*0,2\006\020!\032\002H*H\002¢\006\002\020-J\025\020)\032\0020\0372\006\020!\032\0020\033H\000¢\006\002\b.J\031\020)\032\0020\0372\n\020!\032\0060\032R\0020\033H\000¢\006\002\b.J\b\020/\032\00200H\002J\f\0201\032\b\022\004\022\0020302J\006\0204\032\0020\020J\f\0205\032\b\022\004\022\0020302J\006\0206\032\0020\020R\021\020\002\032\0020\0038G¢\006\006\032\004\b\002\020\006R\020\020\007\032\004\030\0010\003X\016¢\006\002\n\000R*\020\n\032\004\030\0010\t2\b\020\b\032\004\030\0010\t8F@FX\016¢\006\016\n\000\032\004\b\013\020\f\"\004\b\r\020\016R&\020\017\032\0020\0202\006\020\017\032\0020\0208F@FX\016¢\006\016\n\000\032\004\b\021\020\022\"\004\b\023\020\024R&\020\025\032\0020\0202\006\020\025\032\0020\0208F@FX\016¢\006\016\n\000\032\004\b\026\020\022\"\004\b\027\020\024R\030\020\030\032\f\022\b\022\0060\032R\0020\0330\031X\004¢\006\002\n\000R\030\020\034\032\f\022\b\022\0060\032R\0020\0330\031X\004¢\006\002\n\000R\024\020\035\032\b\022\004\022\0020\0330\031X\004¢\006\002\n\000¨\0067"}, d2 = {"Lokhttp3/Dispatcher;", "", "executorService", "Ljava/util/concurrent/ExecutorService;", "(Ljava/util/concurrent/ExecutorService;)V", "()V", "()Ljava/util/concurrent/ExecutorService;", "executorServiceOrNull", "<set-?>", "Ljava/lang/Runnable;", "idleCallback", "getIdleCallback", "()Ljava/lang/Runnable;", "setIdleCallback", "(Ljava/lang/Runnable;)V", "maxRequests", "", "getMaxRequests", "()I", "setMaxRequests", "(I)V", "maxRequestsPerHost", "getMaxRequestsPerHost", "setMaxRequestsPerHost", "readyAsyncCalls", "Ljava/util/ArrayDeque;", "Lokhttp3/internal/connection/RealCall$AsyncCall;", "Lokhttp3/internal/connection/RealCall;", "runningAsyncCalls", "runningSyncCalls", "cancelAll", "", "enqueue", "call", "enqueue$okhttp", "executed", "executed$okhttp", "-deprecated_executorService", "findExistingCallWithHost", "host", "", "finished", "T", "calls", "Ljava/util/Deque;", "(Ljava/util/Deque;Ljava/lang/Object;)V", "finished$okhttp", "promoteAndExecute", "", "queuedCalls", "", "Lokhttp3/Call;", "queuedCallsCount", "runningCalls", "runningCallsCount", "okhttp"})
/*     */ public final class Dispatcher
/*     */ {
/*  46 */   private int maxRequests = 64; public final synchronized int getMaxRequests() { return this.maxRequests; }
/*     */   
/*  48 */   public final void setMaxRequests(int maxRequests) { boolean bool1 = (maxRequests >= 1) ? true : false, bool2 = false; null = false; if (!bool1)
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
/* 242 */       int $i$a$-require-Dispatcher$maxRequests$1 = 0; String str = "max < 1: " + maxRequests; throw (Throwable)new IllegalArgumentException(str.toString()); }  Dispatcher dispatcher = this; bool2 = false; synchronized (false) { int $i$a$-synchronized-Dispatcher$maxRequests$2 = 0; this.maxRequests = maxRequests; Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/Dispatcher}, name=null} */ }  promoteAndExecute(); } private int maxRequestsPerHost = 5; @Nullable private Runnable idleCallback; private ExecutorService executorServiceOrNull; public final synchronized int getMaxRequestsPerHost() { return this.maxRequestsPerHost; } @Nullable public final synchronized Runnable getIdleCallback() { return this.idleCallback; } public final synchronized void setIdleCallback(@Nullable Runnable <set-?>) { this.idleCallback = <set-?>; } @JvmName(name = "executorService") @NotNull public final synchronized ExecutorService executorService() { if (this.executorServiceOrNull == null) this.executorServiceOrNull = new ThreadPoolExecutor(0, 2147483647, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), Util.threadFactory(Util.okHttpName + " Dispatcher", false));  Intrinsics.checkNotNull(this.executorServiceOrNull); return this.executorServiceOrNull; } private final ArrayDeque<RealCall.AsyncCall> readyAsyncCalls = new ArrayDeque<>(); private final ArrayDeque<RealCall.AsyncCall> runningAsyncCalls = new ArrayDeque<>(); public final void setMaxRequestsPerHost(int maxRequestsPerHost) { boolean bool1 = (maxRequestsPerHost >= 1) ? true : false, bool2 = false; null = false; if (!bool1) { int $i$a$-require-Dispatcher$maxRequestsPerHost$1 = 0; String str = "max < 1: " + maxRequestsPerHost; throw (Throwable)new IllegalArgumentException(str.toString()); }  Dispatcher dispatcher = this; bool2 = false; synchronized (false) { int $i$a$-synchronized-Dispatcher$maxRequestsPerHost$2 = 0; this.maxRequestsPerHost = maxRequestsPerHost; Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/Dispatcher}, name=null} */ }  promoteAndExecute(); }
/* 243 */   private final ArrayDeque<RealCall> runningSyncCalls = new ArrayDeque<>(); public Dispatcher(@NotNull ExecutorService executorService) { this(); this.executorServiceOrNull = executorService; } public final void enqueue$okhttp(@NotNull RealCall.AsyncCall call) { Intrinsics.checkNotNullParameter(call, "call"); Dispatcher dispatcher = this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-Dispatcher$enqueue$1 = 0; this.readyAsyncCalls.add(call); if (!call.getCall().getForWebSocket()) { RealCall.AsyncCall existingCall = findExistingCallWithHost(call.getHost()); if (existingCall != null) call.reuseCallsPerHostFrom(existingCall);  }  Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/Dispatcher}, name=null} */ }  promoteAndExecute(); } private final RealCall.AsyncCall findExistingCallWithHost(String host) { for (RealCall.AsyncCall existingCall : this.runningAsyncCalls) { if (Intrinsics.areEqual(existingCall.getHost(), host)) return existingCall;  }  for (RealCall.AsyncCall existingCall : this.readyAsyncCalls) { if (Intrinsics.areEqual(existingCall.getHost(), host)) return existingCall;  }  return null; } public final synchronized void cancelAll() { for (RealCall.AsyncCall call : this.readyAsyncCalls) call.getCall().cancel();  for (RealCall.AsyncCall call : this.runningAsyncCalls) call.getCall().cancel();  for (RealCall call : this.runningSyncCalls) call.cancel();  } private final boolean promoteAndExecute() { Object $this$assertThreadDoesntHoldLock$iv = this; int $i$f$assertThreadDoesntHoldLock = 0; if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
/* 244 */     { Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); }  $i$f$assertThreadDoesntHoldLock = 0; List<RealCall.AsyncCall> executableCalls = new ArrayList(); boolean isRunning = false; Dispatcher dispatcher = this; int i = 0; synchronized (false) { int $i$a$-synchronized-Dispatcher$promoteAndExecute$1 = 0; Intrinsics.checkNotNullExpressionValue(this.readyAsyncCalls.iterator(), "readyAsyncCalls.iterator()"); Iterator<RealCall.AsyncCall> iterator = this.readyAsyncCalls.iterator(); while (iterator.hasNext()) { RealCall.AsyncCall asyncCall = iterator.next(); if (this.runningAsyncCalls.size() >= this.maxRequests) break;  if (asyncCall.getCallsPerHost().get() >= this.maxRequestsPerHost) continue;  iterator.remove(); asyncCall.getCallsPerHost().incrementAndGet(); Intrinsics.checkNotNullExpressionValue(asyncCall, "asyncCall"); executableCalls.add(asyncCall); this.runningAsyncCalls.add(asyncCall); }  isRunning = (runningCallsCount() > 0); Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/Dispatcher}, name=null} */ }  for (byte b = 0; b < i; b++) { RealCall.AsyncCall asyncCall = executableCalls.get(b); asyncCall.executeOn(executorService()); }  return isRunning; } public final synchronized void executed$okhttp(@NotNull RealCall call) { Intrinsics.checkNotNullParameter(call, "call"); this.runningSyncCalls.add(call); } public final void finished$okhttp(@NotNull RealCall.AsyncCall call) { Intrinsics.checkNotNullParameter(call, "call"); call.getCallsPerHost().decrementAndGet(); finished(this.runningAsyncCalls, call); }
/*     */   public final void finished$okhttp(@NotNull RealCall call) { Intrinsics.checkNotNullParameter(call, "call"); finished(this.runningSyncCalls, call); }
/*     */   private final <T> void finished(Deque calls, Object call) { Runnable idleCallback = null; Dispatcher dispatcher = this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-Dispatcher$finished$1 = 0; if (!calls.remove(call)) throw new AssertionError("Call wasn't in-flight!");  idleCallback = this.idleCallback; Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/Dispatcher}, name=null} */ }  boolean isRunning = promoteAndExecute(); if (!isRunning && idleCallback != null) idleCallback.run();  }
/* 247 */   @NotNull public final synchronized List<Call> queuedCalls() { Iterable<RealCall.AsyncCall> $this$map$iv = this.readyAsyncCalls; int $i$f$map = 0; Iterable<RealCall.AsyncCall> iterable1 = $this$map$iv; Collection<RealCall> destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)); int $i$f$mapTo = 0;
/* 248 */     for (RealCall.AsyncCall item$iv$iv : iterable1) {
/* 249 */       RealCall.AsyncCall asyncCall1 = item$iv$iv; Collection<RealCall> collection = destination$iv$iv; int $i$a$-map-Dispatcher$queuedCalls$1 = 0; RealCall realCall = asyncCall1.getCall(); collection.add(realCall);
/* 250 */     }  Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableList((List)destination$iv$iv), "Collections.unmodifiable…yncCalls.map { it.call })"); return (List)Collections.unmodifiableList((List)destination$iv$iv); } @NotNull public final synchronized List<Call> runningCalls() { ArrayDeque<RealCall.AsyncCall> arrayDeque1 = this.runningAsyncCalls; ArrayDeque<RealCall> arrayDeque = this.runningSyncCalls; int $i$f$map = 0;
/* 251 */     ArrayDeque<RealCall.AsyncCall> arrayDeque2 = arrayDeque1; Collection<RealCall> destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayDeque1, 10)); int $i$f$mapTo = 0;
/* 252 */     for (RealCall.AsyncCall item$iv$iv : arrayDeque2) {
/* 253 */       RealCall.AsyncCall asyncCall1 = item$iv$iv; Collection<RealCall> collection = destination$iv$iv; int $i$a$-map-Dispatcher$runningCalls$1 = 0; RealCall realCall = asyncCall1.getCall(); collection.add(realCall);
/* 254 */     }  List list = (List)destination$iv$iv;
/*     */     Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableList(CollectionsKt.plus(arrayDeque, list)), "Collections.unmodifiable…yncCalls.map { it.call })");
/*     */     return (List)Collections.unmodifiableList(CollectionsKt.plus(arrayDeque, list)); }
/*     */ 
/*     */   
/*     */   public final synchronized int queuedCallsCount() {
/*     */     return this.readyAsyncCalls.size();
/*     */   }
/*     */   
/*     */   public final synchronized int runningCallsCount() {
/*     */     return this.runningAsyncCalls.size() + this.runningSyncCalls.size();
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "executorService"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_executorService")
/*     */   @NotNull
/*     */   public final ExecutorService -deprecated_executorService() {
/*     */     return executorService();
/*     */   }
/*     */   
/*     */   public Dispatcher() {}
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/Dispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */