/*     */ package okhttp3.internal.concurrent;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.SynchronousQueue;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.Unit;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.jvm.JvmField;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.internal.Util;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000J\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\004\n\002\020!\n\002\030\002\n\000\n\002\020\013\n\000\n\002\020\t\n\000\n\002\020\b\n\002\b\002\n\002\030\002\n\000\n\002\020 \n\000\n\002\020\002\n\000\n\002\030\002\n\002\b\r\030\000 #2\0020\001:\003\"#$B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\f\020\023\032\b\022\004\022\0020\t0\024J\030\020\025\032\0020\0262\006\020\027\032\0020\0302\006\020\031\032\0020\rH\002J\b\020\032\032\004\030\0010\030J\020\020\033\032\0020\0262\006\020\027\032\0020\030H\002J\006\020\034\032\0020\026J\025\020\035\032\0020\0262\006\020\036\032\0020\tH\000¢\006\002\b\037J\006\020 \032\0020\tJ\020\020!\032\0020\0262\006\020\027\032\0020\030H\002R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\005\020\006R\024\020\007\032\b\022\004\022\0020\t0\bX\004¢\006\002\n\000R\016\020\n\032\0020\013X\016¢\006\002\n\000R\016\020\f\032\0020\rX\016¢\006\002\n\000R\016\020\016\032\0020\017X\016¢\006\002\n\000R\024\020\020\032\b\022\004\022\0020\t0\bX\004¢\006\002\n\000R\016\020\021\032\0020\022X\004¢\006\002\n\000¨\006%"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner;", "", "backend", "Lokhttp3/internal/concurrent/TaskRunner$Backend;", "(Lokhttp3/internal/concurrent/TaskRunner$Backend;)V", "getBackend", "()Lokhttp3/internal/concurrent/TaskRunner$Backend;", "busyQueues", "", "Lokhttp3/internal/concurrent/TaskQueue;", "coordinatorWaiting", "", "coordinatorWakeUpAt", "", "nextQueueName", "", "readyQueues", "runnable", "Ljava/lang/Runnable;", "activeQueues", "", "afterRun", "", "task", "Lokhttp3/internal/concurrent/Task;", "delayNanos", "awaitTaskToRun", "beforeRun", "cancelAll", "kickCoordinator", "taskQueue", "kickCoordinator$okhttp", "newQueue", "runTask", "Backend", "Companion", "RealBackend", "okhttp"})
/*     */ public final class TaskRunner
/*     */ {
/*     */   private int nextQueueName;
/*     */   private boolean coordinatorWaiting;
/*     */   private long coordinatorWakeUpAt;
/*     */   private final List<TaskQueue> busyQueues;
/*     */   private final List<TaskQueue> readyQueues;
/*     */   private final Runnable runnable;
/*     */   @NotNull
/*     */   private final Backend backend;
/*     */   @JvmField
/*     */   @NotNull
/*     */   public static final TaskRunner INSTANCE;
/*     */   @NotNull
/*     */   private static final Logger logger;
/*     */   
/*     */   public TaskRunner(@NotNull Backend backend) {
/*  42 */     this.backend = backend;
/*     */ 
/*     */     
/*  45 */     this.nextQueueName = 10000;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  50 */     boolean bool = false; this.busyQueues = new ArrayList<>();
/*     */ 
/*     */     
/*  53 */     bool = false; this.readyQueues = new ArrayList<>();
/*     */     
/*  55 */     this.runnable = new TaskRunner$runnable$1();
/*     */   } @NotNull
/*     */   public final Backend getBackend() { return this.backend; } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\021\n\000\n\002\030\002\n\000\n\002\020\002\n\000*\001\000\b\n\030\0002\0020\001J\b\020\002\032\0020\003H\026¨\006\004"}, d2 = {"okhttp3/internal/concurrent/TaskRunner$runnable$1", "Ljava/lang/Runnable;", "run", "", "okhttp"})
/*  58 */   public static final class TaskRunner$runnable$1 implements Runnable { public void run() { TaskRunner taskRunner = TaskRunner.this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-TaskRunner$runnable$1$run$task$1 = 0; Task task = 
/*  59 */           TaskRunner.this.awaitTaskToRun(); /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/concurrent/TaskRunner}, name=null} */ }
/*     */        if (task != null) {
/*     */         Task task = task;
/*  62 */         Intrinsics.checkNotNull(task.getQueue$okhttp()); TaskQueue queue$iv = task.getQueue$okhttp(); int $i$f$logElapsed = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 315 */         long startNs$iv = -1L;
/* 316 */         boolean loggingEnabled$iv = TaskRunner.Companion.getLogger().isLoggable(Level.FINE);
/* 317 */         if (loggingEnabled$iv) {
/* 318 */           startNs$iv = queue$iv.getTaskRunner$okhttp().getBackend().nanoTime();
/* 319 */           TaskLoggerKt.access$log(task, queue$iv, "starting");
/*     */         } 
/*     */         
/* 322 */         boolean completedNormally$iv = false; try {
/*     */           Object result$iv;
/* 324 */           int $i$a$-logElapsed-TaskRunner$runnable$1$run$1 = 0; boolean completedNormally = false; try { TaskRunner.this.runTask(task); completedNormally = true; } finally { TaskRunner.this.getBackend().execute(this); }
/* 325 */            completedNormally$iv = true;
/* 326 */           Object object1 = result$iv;
/*     */         } finally {
/* 328 */           if (loggingEnabled$iv)
/* 329 */           { long elapsedNs$iv = queue$iv.getTaskRunner$okhttp().getBackend().nanoTime() - startNs$iv;
/* 330 */             if (completedNormally$iv)
/* 331 */             { TaskLoggerKt.access$log(task, queue$iv, "finished run in " + TaskLoggerKt.formatDuration(elapsedNs$iv)); }
/*     */             else
/* 333 */             { TaskLoggerKt.access$log(task, queue$iv, "failed a run in " + TaskLoggerKt.formatDuration(elapsedNs$iv)); }  } 
/*     */         } 
/* 335 */       }  } } @NotNull public final TaskQueue newQueue() { TaskRunner taskRunner = this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-TaskRunner$newQueue$name$1 = 0;
/*     */       int i;
/*     */       this.nextQueueName = (i = this.nextQueueName) + 1;
/*     */       null = i;
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/concurrent/TaskRunner}, name=null} */ }
/*     */     
/*     */     int name = null;
/*     */     return new TaskQueue(this, 'Q' + name); }
/*     */ 
/*     */   
/*     */   public final void kickCoordinator$okhttp(@NotNull TaskQueue taskQueue) {
/*     */     Intrinsics.checkNotNullParameter(taskQueue, "taskQueue");
/*     */     Object<Task> $this$assertThreadHoldsLock$iv = (Object<Task>)this;
/*     */     int $i$f$assertThreadHoldsLock = 0;
/*     */     if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
/*     */       Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()");
/*     */       throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv);
/*     */     } 
/*     */     if (taskQueue.getActiveTask$okhttp() == null) {
/*     */       $this$assertThreadHoldsLock$iv = (Object<Task>)taskQueue.getFutureTasks$okhttp();
/*     */       $i$f$assertThreadHoldsLock = 0;
/*     */       if (!$this$assertThreadHoldsLock$iv.isEmpty()) {
/*     */         Util.addIfAbsent(this.readyQueues, taskQueue);
/*     */       } else {
/*     */         this.readyQueues.remove(taskQueue);
/*     */       } 
/*     */     } 
/*     */     if (this.coordinatorWaiting) {
/*     */       this.backend.coordinatorNotify(this);
/*     */     } else {
/*     */       this.backend.execute(this.runnable);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void beforeRun(Task task) {
/*     */     Object $this$assertThreadHoldsLock$iv = this;
/*     */     int $i$f$assertThreadHoldsLock = 0;
/*     */     if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
/*     */       Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()");
/*     */       throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv);
/*     */     } 
/*     */     task.setNextExecuteNanoTime$okhttp(-1L);
/*     */     Intrinsics.checkNotNull(task.getQueue$okhttp());
/*     */     TaskQueue queue = task.getQueue$okhttp();
/*     */     queue.getFutureTasks$okhttp().remove(task);
/*     */     this.readyQueues.remove(queue);
/*     */     queue.setActiveTask$okhttp(task);
/*     */     this.busyQueues.add(queue);
/*     */   }
/*     */   
/*     */   private final void runTask(Task task) {
/*     */     Object $this$assertThreadDoesntHoldLock$iv = this;
/*     */     int $i$f$assertThreadDoesntHoldLock = 0;
/*     */     if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) {
/*     */       Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()");
/*     */       throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv);
/*     */     } 
/*     */     Thread currentThread = Thread.currentThread();
/*     */     Intrinsics.checkNotNullExpressionValue(currentThread, "currentThread");
/*     */     String oldName = currentThread.getName();
/*     */     currentThread.setName(task.getName());
/*     */     long delayNanos = -1L;
/*     */     try {
/*     */       TaskRunner taskRunner;
/*     */       delayNanos = task.runOnce();
/*     */     } finally {
/*     */       TaskRunner taskRunner = this;
/*     */       boolean bool = false;
/*     */       synchronized (false) {
/*     */         int $i$a$-synchronized-TaskRunner$runTask$1 = 0;
/*     */         afterRun(task, delayNanos);
/*     */         Unit unit = Unit.INSTANCE;
/*     */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/concurrent/TaskRunner}, name=null} */
/*     */       } 
/*     */       currentThread.setName(oldName);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void afterRun(Task task, long delayNanos) {
/*     */     Object $this$assertThreadHoldsLock$iv = this;
/*     */     int $i$f$assertThreadHoldsLock = 0;
/*     */     if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
/*     */       Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()");
/*     */       throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv);
/*     */     } 
/*     */     Intrinsics.checkNotNull(task.getQueue$okhttp());
/*     */     TaskQueue queue = task.getQueue$okhttp();
/*     */     $i$f$assertThreadHoldsLock = (queue.getActiveTask$okhttp() == task) ? 1 : 0;
/*     */     boolean bool1 = false, bool2 = false;
/*     */     bool2 = false;
/*     */     boolean bool3 = false;
/*     */     if ($i$f$assertThreadHoldsLock == 0) {
/*     */       boolean bool = false;
/*     */       String str = "Check failed.";
/*     */       throw (Throwable)new IllegalStateException(str.toString());
/*     */     } 
/*     */     boolean cancelActiveTask = queue.getCancelActiveTask$okhttp();
/*     */     queue.setCancelActiveTask$okhttp(false);
/*     */     queue.setActiveTask$okhttp((Task)null);
/*     */     this.busyQueues.remove(queue);
/*     */     if (delayNanos != -1L && !cancelActiveTask && !queue.getShutdown$okhttp())
/*     */       queue.scheduleAndDecide$okhttp(task, delayNanos, true); 
/*     */     List<Task> list = queue.getFutureTasks$okhttp();
/*     */     bool2 = false;
/*     */     if (!list.isEmpty())
/*     */       this.readyQueues.add(queue); 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public final Task awaitTaskToRun() {
/*     */     Object $this$assertThreadHoldsLock$iv = this;
/*     */     int $i$f$assertThreadHoldsLock = 0;
/*     */     if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv)) {
/*     */       Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()");
/*     */       throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv);
/*     */     } 
/*     */     while (true) {
/*     */       if (this.readyQueues.isEmpty())
/*     */         return null; 
/*     */       long now = this.backend.nanoTime();
/*     */       long minDelayNanos = Long.MAX_VALUE;
/*     */       Task readyTask = (Task)null;
/*     */       boolean multipleReadyTasks = false;
/*     */       for (TaskQueue queue : this.readyQueues) {
/*     */         Task candidate = queue.getFutureTasks$okhttp().get(0);
/*     */         long l1 = 0L, l2 = candidate.getNextExecuteNanoTime$okhttp() - now;
/*     */         boolean bool = false;
/*     */         long candidateDelay = Math.max(l1, l2);
/*     */         if (candidateDelay > 0L) {
/*     */           boolean bool1 = false;
/*     */           minDelayNanos = Math.min(candidateDelay, minDelayNanos);
/*     */           continue;
/*     */         } 
/*     */         if (readyTask != null) {
/*     */           multipleReadyTasks = true;
/*     */           break;
/*     */         } 
/*     */         readyTask = candidate;
/*     */       } 
/*     */       if (readyTask != null) {
/*     */         beforeRun(readyTask);
/*     */         if (!multipleReadyTasks) {
/*     */           if (!this.coordinatorWaiting) {
/*     */             List<TaskQueue> list = this.readyQueues;
/*     */             boolean bool = false;
/*     */             if (!list.isEmpty()) {
/*     */               this.backend.execute(this.runnable);
/*     */               return readyTask;
/*     */             } 
/*     */           } 
/*     */           return readyTask;
/*     */         } 
/*     */       } else {
/*     */         if (this.coordinatorWaiting) {
/*     */           if (minDelayNanos < this.coordinatorWakeUpAt - now)
/*     */             this.backend.coordinatorNotify(this); 
/*     */           return null;
/*     */         } 
/*     */         this.coordinatorWaiting = true;
/*     */         this.coordinatorWakeUpAt = now + minDelayNanos;
/*     */         try {
/*     */           this.backend.coordinatorWait(this, minDelayNanos);
/*     */         } catch (InterruptedException _) {
/*     */           cancelAll();
/*     */         } finally {
/*     */           this.coordinatorWaiting = false;
/*     */         } 
/*     */         continue;
/*     */       } 
/*     */       this.backend.execute(this.runnable);
/*     */       return readyTask;
/*     */     } 
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public final List<TaskQueue> activeQueues() {
/*     */     TaskRunner taskRunner = this;
/*     */     boolean bool = false;
/*     */     synchronized (false) {
/*     */       int $i$a$-synchronized-TaskRunner$activeQueues$1 = 0;
/*     */       List<TaskQueue> list = CollectionsKt.plus(this.busyQueues, this.readyQueues);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/concurrent/TaskRunner}, name=null} */
/*     */       return list;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void cancelAll() {
/*     */     boolean bool;
/*     */     for (int j = this.busyQueues.size() - 1; j >= 0; j--)
/*     */       ((TaskQueue)this.busyQueues.get(j)).cancelAllAndDecide$okhttp(); 
/*     */     for (int i = this.readyQueues.size() - 1; i >= 0; i--) {
/*     */       TaskQueue queue = this.readyQueues.get(i);
/*     */       queue.cancelAllAndDecide$okhttp();
/*     */       if (queue.getFutureTasks$okhttp().isEmpty())
/*     */         this.readyQueues.remove(i); 
/*     */     } 
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0006\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\002\b\003\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\003\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\020\020\007\032\0020\b2\006\020\t\032\0020\nH\026J\020\020\013\032\0020\b2\006\020\t\032\0020\nH\026J\030\020\f\032\0020\b2\006\020\t\032\0020\n2\006\020\r\032\0020\016H\026J\020\020\017\032\0020\b2\006\020\020\032\0020\021H\026J\b\020\022\032\0020\016H\026J\006\020\023\032\0020\bR\016\020\005\032\0020\006X\004¢\006\002\n\000¨\006\024"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner$RealBackend;", "Lokhttp3/internal/concurrent/TaskRunner$Backend;", "threadFactory", "Ljava/util/concurrent/ThreadFactory;", "(Ljava/util/concurrent/ThreadFactory;)V", "executor", "Ljava/util/concurrent/ThreadPoolExecutor;", "beforeTask", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "coordinatorNotify", "coordinatorWait", "nanos", "", "execute", "runnable", "Ljava/lang/Runnable;", "nanoTime", "shutdown", "okhttp"})
/*     */   public static final class RealBackend implements Backend {
/*     */     private final ThreadPoolExecutor executor;
/*     */     
/*     */     public RealBackend(@NotNull ThreadFactory threadFactory) {
/*     */       this.executor = new ThreadPoolExecutor(0, 2147483647, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), threadFactory);
/*     */     }
/*     */     
/*     */     public void beforeTask(@NotNull TaskRunner taskRunner) {
/*     */       Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
/*     */     }
/*     */     
/*     */     public long nanoTime() {
/*     */       return System.nanoTime();
/*     */     }
/*     */     
/*     */     public void coordinatorNotify(@NotNull TaskRunner taskRunner) {
/*     */       Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
/*     */       Object $this$notify$iv = taskRunner;
/*     */       int $i$f$notify = 0;
/*     */       $this$notify$iv.notify();
/*     */     }
/*     */     
/*     */     public void coordinatorWait(@NotNull TaskRunner taskRunner, long nanos) throws InterruptedException {
/*     */       Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
/*     */       long ms = nanos / 1000000L;
/*     */       long ns = nanos - ms * 1000000L;
/*     */       if (ms > 0L || nanos > 0L)
/*     */         taskRunner.wait(ms, (int)ns); 
/*     */     }
/*     */     
/*     */     public void execute(@NotNull Runnable runnable) {
/*     */       Intrinsics.checkNotNullParameter(runnable, "runnable");
/*     */       this.executor.execute(runnable);
/*     */     }
/*     */     
/*     */     public final void shutdown() {
/*     */       this.executor.shutdown();
/*     */     }
/*     */   }
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   static {
/*     */     INSTANCE = new TaskRunner(new RealBackend(Util.threadFactory(Util.okHttpName + " TaskRunner", true)));
/*     */     Intrinsics.checkNotNullExpressionValue(Logger.getLogger(TaskRunner.class.getName()), "Logger.getLogger(TaskRunner::class.java.name)");
/*     */     logger = Logger.getLogger(TaskRunner.class.getName());
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\020\020\003\032\0020\0048\006X\004¢\006\002\n\000R\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\007\020\b¨\006\t"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner$Companion;", "", "()V", "INSTANCE", "Lokhttp3/internal/concurrent/TaskRunner;", "logger", "Ljava/util/logging/Logger;", "getLogger", "()Ljava/util/logging/Logger;", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */     
/*     */     @NotNull
/*     */     public final Logger getLogger() {
/*     */       return TaskRunner.logger;
/*     */     }
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000(\n\002\030\002\n\002\020\000\n\000\n\002\020\002\n\000\n\002\030\002\n\002\b\003\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\002\bf\030\0002\0020\001J\020\020\002\032\0020\0032\006\020\004\032\0020\005H&J\020\020\006\032\0020\0032\006\020\004\032\0020\005H&J\030\020\007\032\0020\0032\006\020\004\032\0020\0052\006\020\b\032\0020\tH&J\020\020\n\032\0020\0032\006\020\013\032\0020\fH&J\b\020\r\032\0020\tH&¨\006\016"}, d2 = {"Lokhttp3/internal/concurrent/TaskRunner$Backend;", "", "beforeTask", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "coordinatorNotify", "coordinatorWait", "nanos", "", "execute", "runnable", "Ljava/lang/Runnable;", "nanoTime", "okhttp"})
/*     */   public static interface Backend {
/*     */     void beforeTask(@NotNull TaskRunner param1TaskRunner);
/*     */     
/*     */     long nanoTime();
/*     */     
/*     */     void coordinatorNotify(@NotNull TaskRunner param1TaskRunner);
/*     */     
/*     */     void coordinatorWait(@NotNull TaskRunner param1TaskRunner, long param1Long);
/*     */     
/*     */     void execute(@NotNull Runnable param1Runnable);
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/concurrent/TaskRunner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */