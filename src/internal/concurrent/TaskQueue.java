/*     */ package okhttp3.internal.concurrent;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CountDownLatch;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.Unit;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.jvm.functions.Function0;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.internal.Util;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000V\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\005\n\002\020\013\n\002\b\005\n\002\020!\n\002\b\005\n\002\020 \n\002\b\007\n\002\020\002\n\002\b\004\n\002\020\t\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\b\030\0002\0020\001:\0013B\027\b\000\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005¢\006\002\020\006J\006\020!\032\0020\"J\r\020#\032\0020\016H\000¢\006\002\b$J8\020%\032\0020\"2\006\020\004\032\0020\0052\b\b\002\020&\032\0020'2\b\b\002\020(\032\0020\0162\016\b\004\020)\032\b\022\004\022\0020\"0*H\bø\001\000J\006\020+\032\0020,J.\020-\032\0020\"2\006\020\004\032\0020\0052\b\b\002\020&\032\0020'2\016\b\004\020)\032\b\022\004\022\0020'0*H\bø\001\000J\030\020-\032\0020\"2\006\020.\032\0020\b2\b\b\002\020&\032\0020'J%\020/\032\0020\0162\006\020.\032\0020\b2\006\020&\032\0020'2\006\0200\032\0020\016H\000¢\006\002\b1J\006\020\034\032\0020\"J\b\0202\032\0020\005H\026R\034\020\007\032\004\030\0010\bX\016¢\006\016\n\000\032\004\b\t\020\n\"\004\b\013\020\fR\032\020\r\032\0020\016X\016¢\006\016\n\000\032\004\b\017\020\020\"\004\b\021\020\022R\032\020\023\032\b\022\004\022\0020\b0\024X\004¢\006\b\n\000\032\004\b\025\020\026R\024\020\004\032\0020\005X\004¢\006\b\n\000\032\004\b\027\020\030R\027\020\031\032\b\022\004\022\0020\b0\0328F¢\006\006\032\004\b\033\020\026R\032\020\034\032\0020\016X\016¢\006\016\n\000\032\004\b\035\020\020\"\004\b\036\020\022R\024\020\002\032\0020\003X\004¢\006\b\n\000\032\004\b\037\020 \002\007\n\005\b20\001¨\0064"}, d2 = {"Lokhttp3/internal/concurrent/TaskQueue;", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "name", "", "(Lokhttp3/internal/concurrent/TaskRunner;Ljava/lang/String;)V", "activeTask", "Lokhttp3/internal/concurrent/Task;", "getActiveTask$okhttp", "()Lokhttp3/internal/concurrent/Task;", "setActiveTask$okhttp", "(Lokhttp3/internal/concurrent/Task;)V", "cancelActiveTask", "", "getCancelActiveTask$okhttp", "()Z", "setCancelActiveTask$okhttp", "(Z)V", "futureTasks", "", "getFutureTasks$okhttp", "()Ljava/util/List;", "getName$okhttp", "()Ljava/lang/String;", "scheduledTasks", "", "getScheduledTasks", "shutdown", "getShutdown$okhttp", "setShutdown$okhttp", "getTaskRunner$okhttp", "()Lokhttp3/internal/concurrent/TaskRunner;", "cancelAll", "", "cancelAllAndDecide", "cancelAllAndDecide$okhttp", "execute", "delayNanos", "", "cancelable", "block", "Lkotlin/Function0;", "idleLatch", "Ljava/util/concurrent/CountDownLatch;", "schedule", "task", "scheduleAndDecide", "recurrence", "scheduleAndDecide$okhttp", "toString", "AwaitIdleTask", "okhttp"})
/*     */ public final class TaskQueue {
/*     */   private boolean shutdown;
/*     */   @Nullable
/*     */   private Task activeTask;
/*     */   @NotNull
/*     */   private final List<Task> futureTasks;
/*     */   private boolean cancelActiveTask;
/*     */   @NotNull
/*     */   private final TaskRunner taskRunner;
/*     */   @NotNull
/*     */   private final String name;
/*     */   
/*     */   public TaskQueue(@NotNull TaskRunner taskRunner, @NotNull String name) {
/*  29 */     this.taskRunner = taskRunner; this.name = name;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  39 */     boolean bool = false; this.futureTasks = new ArrayList<>(); } @NotNull public final TaskRunner getTaskRunner$okhttp() { return this.taskRunner; } @NotNull public final String getName$okhttp() { return this.name; } public final boolean getShutdown$okhttp() { return this.shutdown; } public final void setShutdown$okhttp(boolean <set-?>) { this.shutdown = <set-?>; } @Nullable public final Task getActiveTask$okhttp() { return this.activeTask; } public final void setActiveTask$okhttp(@Nullable Task <set-?>) { this.activeTask = <set-?>; } @NotNull public final List<Task> getFutureTasks$okhttp() { return this.futureTasks; }
/*     */ 
/*     */   
/*  42 */   public final boolean getCancelActiveTask$okhttp() { return this.cancelActiveTask; } public final void setCancelActiveTask$okhttp(boolean <set-?>) { this.cancelActiveTask = <set-?>; } public final void schedule(@NotNull Task task, long delayNanos) { // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: ldc 'task'
/*     */     //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */     //   6: aload_0
/*     */     //   7: getfield taskRunner : Lokhttp3/internal/concurrent/TaskRunner;
/*     */     //   10: astore #4
/*     */     //   12: iconst_0
/*     */     //   13: istore #5
/*     */     //   15: iconst_0
/*     */     //   16: istore #6
/*     */     //   18: aload #4
/*     */     //   20: monitorenter
/*     */     //   21: nop
/*     */     //   22: iconst_0
/*     */     //   23: istore #7
/*     */     //   25: aload_0
/*     */     //   26: getfield shutdown : Z
/*     */     //   29: ifeq -> 152
/*     */     //   32: aload_1
/*     */     //   33: invokevirtual getCancelable : ()Z
/*     */     //   36: ifeq -> 92
/*     */     //   39: aload_1
/*     */     //   40: astore #8
/*     */     //   42: aload_0
/*     */     //   43: astore #9
/*     */     //   45: iconst_0
/*     */     //   46: istore #10
/*     */     //   48: getstatic okhttp3/internal/concurrent/TaskRunner.Companion : Lokhttp3/internal/concurrent/TaskRunner$Companion;
/*     */     //   51: invokevirtual getLogger : ()Ljava/util/logging/Logger;
/*     */     //   54: getstatic java/util/logging/Level.FINE : Ljava/util/logging/Level;
/*     */     //   57: invokevirtual isLoggable : (Ljava/util/logging/Level;)Z
/*     */     //   60: ifeq -> 87
/*     */     //   63: aload #8
/*     */     //   65: aload #9
/*     */     //   67: astore #11
/*     */     //   69: astore #12
/*     */     //   71: iconst_0
/*     */     //   72: istore #13
/*     */     //   74: ldc 'schedule canceled (queue is shutdown)'
/*     */     //   76: astore #14
/*     */     //   78: aload #12
/*     */     //   80: aload #11
/*     */     //   82: aload #14
/*     */     //   84: invokestatic access$log : (Lokhttp3/internal/concurrent/Task;Lokhttp3/internal/concurrent/TaskQueue;Ljava/lang/String;)V
/*     */     //   87: nop
/*     */     //   88: aload #4
/*     */     //   90: monitorexit
/*     */     //   91: return
/*     */     //   92: aload_1
/*     */     //   93: astore #8
/*     */     //   95: aload_0
/*     */     //   96: astore #9
/*     */     //   98: iconst_0
/*     */     //   99: istore #10
/*     */     //   101: getstatic okhttp3/internal/concurrent/TaskRunner.Companion : Lokhttp3/internal/concurrent/TaskRunner$Companion;
/*     */     //   104: invokevirtual getLogger : ()Ljava/util/logging/Logger;
/*     */     //   107: getstatic java/util/logging/Level.FINE : Ljava/util/logging/Level;
/*     */     //   110: invokevirtual isLoggable : (Ljava/util/logging/Level;)Z
/*     */     //   113: ifeq -> 140
/*     */     //   116: aload #8
/*     */     //   118: aload #9
/*     */     //   120: astore #11
/*     */     //   122: astore #12
/*     */     //   124: iconst_0
/*     */     //   125: istore #13
/*     */     //   127: ldc 'schedule failed (queue is shutdown)'
/*     */     //   129: astore #14
/*     */     //   131: aload #12
/*     */     //   133: aload #11
/*     */     //   135: aload #14
/*     */     //   137: invokestatic access$log : (Lokhttp3/internal/concurrent/Task;Lokhttp3/internal/concurrent/TaskQueue;Ljava/lang/String;)V
/*     */     //   140: nop
/*     */     //   141: new java/util/concurrent/RejectedExecutionException
/*     */     //   144: dup
/*     */     //   145: invokespecial <init> : ()V
/*     */     //   148: checkcast java/lang/Throwable
/*     */     //   151: athrow
/*     */     //   152: aload_0
/*     */     //   153: aload_1
/*     */     //   154: lload_2
/*     */     //   155: iconst_0
/*     */     //   156: invokevirtual scheduleAndDecide$okhttp : (Lokhttp3/internal/concurrent/Task;JZ)Z
/*     */     //   159: ifeq -> 170
/*     */     //   162: aload_0
/*     */     //   163: getfield taskRunner : Lokhttp3/internal/concurrent/TaskRunner;
/*     */     //   166: aload_0
/*     */     //   167: invokevirtual kickCoordinator$okhttp : (Lokhttp3/internal/concurrent/TaskQueue;)V
/*     */     //   170: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
/*     */     //   173: astore #6
/*     */     //   175: aload #4
/*     */     //   177: monitorexit
/*     */     //   178: goto -> 189
/*     */     //   181: astore #6
/*     */     //   183: aload #4
/*     */     //   185: monitorexit
/*     */     //   186: aload #6
/*     */     //   188: athrow
/*     */     //   189: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #62	-> 6
/*     */     //   #63	-> 25
/*     */     //   #64	-> 32
/*     */     //   #65	-> 39
/*     */     //   #220	-> 48
/*     */     //   #221	-> 63
/*     */     //   #65	-> 74
/*     */     //   #223	-> 87
/*     */     //   #66	-> 88
/*     */     //   #68	-> 92
/*     */     //   #224	-> 101
/*     */     //   #225	-> 116
/*     */     //   #68	-> 127
/*     */     //   #227	-> 140
/*     */     //   #69	-> 141
/*     */     //   #72	-> 152
/*     */     //   #73	-> 162
/*     */     //   #75	-> 170
/*     */     //   #62	-> 173
/*     */     //   #76	-> 189
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   74	2	13	$i$a$-taskLog-TaskQueue$schedule$1$1	I
/*     */     //   45	43	8	task$iv	Lokhttp3/internal/concurrent/Task;
/*     */     //   45	43	9	queue$iv	Lokhttp3/internal/concurrent/TaskQueue;
/*     */     //   48	40	10	$i$f$taskLog	I
/*     */     //   127	2	13	$i$a$-taskLog-TaskQueue$schedule$1$2	I
/*     */     //   98	43	8	task$iv	Lokhttp3/internal/concurrent/Task;
/*     */     //   98	43	9	queue$iv	Lokhttp3/internal/concurrent/TaskQueue;
/*     */     //   101	40	10	$i$f$taskLog	I
/*     */     //   25	63	7	$i$a$-synchronized-TaskQueue$schedule$1	I
/*     */     //   92	78	7	$i$a$-synchronized-TaskQueue$schedule$1	I
/*     */     //   0	190	0	this	Lokhttp3/internal/concurrent/TaskQueue;
/*     */     //   0	190	1	task	Lokhttp3/internal/concurrent/Task;
/*     */     //   0	190	2	delayNanos	J
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   21	88	181	finally
/*     */     //   92	175	181	finally
/*     */     //   181	183	181	finally }
/*     */   public final void schedule(@NotNull String name, long delayNanos, @NotNull Function0 block) { int $i$f$schedule = 0; Intrinsics.checkNotNullParameter(name, "name"); Intrinsics.checkNotNullParameter(block, "block"); schedule(new TaskQueue$schedule$2(block, name, name), delayNanos); }
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\021\n\000\n\002\030\002\n\000\n\002\020\t\n\000*\001\000\b\n\030\0002\0020\001J\b\020\002\032\0020\003H\026¨\006\004"}, d2 = {"okhttp3/internal/concurrent/TaskQueue$schedule$2", "Lokhttp3/internal/concurrent/Task;", "runOnce", "", "okhttp"}) public static final class TaskQueue$schedule$2 extends Task {
/*     */     public TaskQueue$schedule$2(Function0 $captured_local_variable$0, String $captured_local_variable$1, String $super_call_param$2) { super($super_call_param$2, false, 2, null); }
/*     */     public long runOnce() { return ((Number)this.$block.invoke()).longValue(); } }
/*     */   public final void execute(@NotNull String name, long delayNanos, boolean cancelable, @NotNull Function0 block) { int $i$f$execute = 0; Intrinsics.checkNotNullParameter(name, "name"); Intrinsics.checkNotNullParameter(block, "block");
/*     */     schedule(new TaskQueue$execute$1(block, name, cancelable, name, cancelable), delayNanos); }
/*  49 */   @NotNull public final List<Task> getScheduledTasks() { TaskRunner taskRunner = this.taskRunner; boolean bool = false; synchronized (false) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 219 */       int $i$a$-synchronized-TaskQueue$scheduledTasks$1 = 0; List<Task> list = CollectionsKt.toList(this.futureTasks); /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/concurrent/TaskRunner}, name=null} */
/*     */     }  return list; }
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\021\n\000\n\002\030\002\n\000\n\002\020\t\n\000*\001\000\b\n\030\0002\0020\001J\b\020\002\032\0020\003H\026¨\006\004"}, d2 = {"okhttp3/internal/concurrent/TaskQueue$execute$1", "Lokhttp3/internal/concurrent/Task;", "runOnce", "", "okhttp"}) public static final class TaskQueue$execute$1 extends Task {
/*     */     public TaskQueue$execute$1(Function0 $captured_local_variable$0, String $captured_local_variable$1, boolean $captured_local_variable$2, String $super_call_param$3, boolean $super_call_param$4) { super($super_call_param$3, $super_call_param$4); }
/*     */     public long runOnce() { this.$block.invoke(); return -1L; } }
/*     */   @NotNull public final CountDownLatch idleLatch() { TaskRunner taskRunner = this.taskRunner; boolean bool = false; synchronized (false) {
/*     */       int $i$a$-synchronized-TaskQueue$idleLatch$1 = 0; if (this.activeTask == null && this.futureTasks.isEmpty()) {
/*     */         CountDownLatch countDownLatch1 = new CountDownLatch(0); /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/concurrent/TaskRunner}, name=null} */ return countDownLatch1;
/*     */       }  Task existingTask = this.activeTask; if (existingTask instanceof AwaitIdleTask) {
/*     */         CountDownLatch countDownLatch1 = ((AwaitIdleTask)existingTask).getLatch(); /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/concurrent/TaskRunner}, name=null} */ return countDownLatch1;
/*     */       }  for (Task futureTask : this.futureTasks) {
/*     */         if (futureTask instanceof AwaitIdleTask) {
/*     */           CountDownLatch countDownLatch1 = ((AwaitIdleTask)futureTask).getLatch(); /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/concurrent/TaskRunner}, name=null} */
/*     */           return countDownLatch1;
/*     */         } 
/*     */       } 
/*     */       AwaitIdleTask newTask = new AwaitIdleTask();
/*     */       if (scheduleAndDecide$okhttp(newTask, 0L, false))
/*     */         this.taskRunner.kickCoordinator$okhttp(this); 
/*     */       CountDownLatch countDownLatch = newTask.getLatch();
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/concurrent/TaskRunner}, name=null} */
/*     */       return countDownLatch;
/*     */     }  } public final void cancelAll() { Object $this$assertThreadDoesntHoldLock$iv = this;
/*     */     int $i$f$assertThreadDoesntHoldLock = 0;
/* 243 */     if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv))
/* 244 */     { Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv); }  $this$assertThreadDoesntHoldLock$iv = this.taskRunner; $i$f$assertThreadDoesntHoldLock = 0; synchronized (false) { int $i$a$-synchronized-TaskQueue$cancelAll$1 = 0; if (cancelAllAndDecide$okhttp()) this.taskRunner.kickCoordinator$okhttp(this);  Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=$this$assertThreadDoesntHoldLock$iv} */ }  }
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\032\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\t\n\000\b\002\030\0002\0020\001B\005¢\006\002\020\002J\b\020\007\032\0020\bH\026R\021\020\003\032\0020\004¢\006\b\n\000\032\004\b\005\020\006¨\006\t"}, d2 = {"Lokhttp3/internal/concurrent/TaskQueue$AwaitIdleTask;", "Lokhttp3/internal/concurrent/Task;", "()V", "latch", "Ljava/util/concurrent/CountDownLatch;", "getLatch", "()Ljava/util/concurrent/CountDownLatch;", "runOnce", "", "okhttp"}) private static final class AwaitIdleTask extends Task {
/*     */     @NotNull private final CountDownLatch latch;
/*     */     public AwaitIdleTask() { super(Util.okHttpName + " awaitIdle", false); this.latch = new CountDownLatch(1); } @NotNull public final CountDownLatch getLatch() { return this.latch; } public long runOnce() { this.latch.countDown(); return -1L; } } public final boolean scheduleAndDecide$okhttp(@NotNull Task task, long delayNanos, boolean recurrence) { // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: ldc 'task'
/*     */     //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */     //   6: aload_1
/*     */     //   7: aload_0
/*     */     //   8: invokevirtual initQueue$okhttp : (Lokhttp3/internal/concurrent/TaskQueue;)V
/*     */     //   11: aload_0
/*     */     //   12: getfield taskRunner : Lokhttp3/internal/concurrent/TaskRunner;
/*     */     //   15: invokevirtual getBackend : ()Lokhttp3/internal/concurrent/TaskRunner$Backend;
/*     */     //   18: invokeinterface nanoTime : ()J
/*     */     //   23: lstore #5
/*     */     //   25: lload #5
/*     */     //   27: lload_2
/*     */     //   28: ladd
/*     */     //   29: lstore #7
/*     */     //   31: aload_0
/*     */     //   32: getfield futureTasks : Ljava/util/List;
/*     */     //   35: aload_1
/*     */     //   36: invokeinterface indexOf : (Ljava/lang/Object;)I
/*     */     //   41: istore #9
/*     */     //   43: iload #9
/*     */     //   45: iconst_m1
/*     */     //   46: if_icmpeq -> 118
/*     */     //   49: aload_1
/*     */     //   50: invokevirtual getNextExecuteNanoTime$okhttp : ()J
/*     */     //   53: lload #7
/*     */     //   55: lcmp
/*     */     //   56: ifgt -> 106
/*     */     //   59: aload_0
/*     */     //   60: astore #10
/*     */     //   62: iconst_0
/*     */     //   63: istore #11
/*     */     //   65: getstatic okhttp3/internal/concurrent/TaskRunner.Companion : Lokhttp3/internal/concurrent/TaskRunner$Companion;
/*     */     //   68: invokevirtual getLogger : ()Ljava/util/logging/Logger;
/*     */     //   71: getstatic java/util/logging/Level.FINE : Ljava/util/logging/Level;
/*     */     //   74: invokevirtual isLoggable : (Ljava/util/logging/Level;)Z
/*     */     //   77: ifeq -> 103
/*     */     //   80: aload_1
/*     */     //   81: aload #10
/*     */     //   83: astore #19
/*     */     //   85: astore #18
/*     */     //   87: iconst_0
/*     */     //   88: istore #12
/*     */     //   90: ldc 'already scheduled'
/*     */     //   92: astore #20
/*     */     //   94: aload #18
/*     */     //   96: aload #19
/*     */     //   98: aload #20
/*     */     //   100: invokestatic access$log : (Lokhttp3/internal/concurrent/Task;Lokhttp3/internal/concurrent/TaskQueue;Ljava/lang/String;)V
/*     */     //   103: nop
/*     */     //   104: iconst_0
/*     */     //   105: ireturn
/*     */     //   106: aload_0
/*     */     //   107: getfield futureTasks : Ljava/util/List;
/*     */     //   110: iload #9
/*     */     //   112: invokeinterface remove : (I)Ljava/lang/Object;
/*     */     //   117: pop
/*     */     //   118: aload_1
/*     */     //   119: lload #7
/*     */     //   121: invokevirtual setNextExecuteNanoTime$okhttp : (J)V
/*     */     //   124: aload_0
/*     */     //   125: astore #10
/*     */     //   127: iconst_0
/*     */     //   128: istore #11
/*     */     //   130: getstatic okhttp3/internal/concurrent/TaskRunner.Companion : Lokhttp3/internal/concurrent/TaskRunner$Companion;
/*     */     //   133: invokevirtual getLogger : ()Ljava/util/logging/Logger;
/*     */     //   136: getstatic java/util/logging/Level.FINE : Ljava/util/logging/Level;
/*     */     //   139: invokevirtual isLoggable : (Ljava/util/logging/Level;)Z
/*     */     //   142: ifeq -> 226
/*     */     //   145: aload_1
/*     */     //   146: aload #10
/*     */     //   148: astore #19
/*     */     //   150: astore #18
/*     */     //   152: iconst_0
/*     */     //   153: istore #12
/*     */     //   155: iload #4
/*     */     //   157: ifeq -> 189
/*     */     //   160: new java/lang/StringBuilder
/*     */     //   163: dup
/*     */     //   164: invokespecial <init> : ()V
/*     */     //   167: ldc 'run again after '
/*     */     //   169: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   172: lload #7
/*     */     //   174: lload #5
/*     */     //   176: lsub
/*     */     //   177: invokestatic formatDuration : (J)Ljava/lang/String;
/*     */     //   180: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   183: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   186: goto -> 215
/*     */     //   189: new java/lang/StringBuilder
/*     */     //   192: dup
/*     */     //   193: invokespecial <init> : ()V
/*     */     //   196: ldc 'scheduled after '
/*     */     //   198: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   201: lload #7
/*     */     //   203: lload #5
/*     */     //   205: lsub
/*     */     //   206: invokestatic formatDuration : (J)Ljava/lang/String;
/*     */     //   209: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   212: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   215: astore #20
/*     */     //   217: aload #18
/*     */     //   219: aload #19
/*     */     //   221: aload #20
/*     */     //   223: invokestatic access$log : (Lokhttp3/internal/concurrent/Task;Lokhttp3/internal/concurrent/TaskQueue;Ljava/lang/String;)V
/*     */     //   226: nop
/*     */     //   227: aload_0
/*     */     //   228: getfield futureTasks : Ljava/util/List;
/*     */     //   231: astore #11
/*     */     //   233: iconst_0
/*     */     //   234: istore #12
/*     */     //   236: iconst_0
/*     */     //   237: istore #13
/*     */     //   239: aload #11
/*     */     //   241: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   246: astore #14
/*     */     //   248: aload #14
/*     */     //   250: invokeinterface hasNext : ()Z
/*     */     //   255: ifeq -> 309
/*     */     //   258: aload #14
/*     */     //   260: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   265: astore #15
/*     */     //   267: aload #15
/*     */     //   269: checkcast okhttp3/internal/concurrent/Task
/*     */     //   272: astore #16
/*     */     //   274: iconst_0
/*     */     //   275: istore #17
/*     */     //   277: aload #16
/*     */     //   279: invokevirtual getNextExecuteNanoTime$okhttp : ()J
/*     */     //   282: lload #5
/*     */     //   284: lsub
/*     */     //   285: lload_2
/*     */     //   286: lcmp
/*     */     //   287: ifle -> 294
/*     */     //   290: iconst_1
/*     */     //   291: goto -> 295
/*     */     //   294: iconst_0
/*     */     //   295: ifeq -> 303
/*     */     //   298: iload #13
/*     */     //   300: goto -> 310
/*     */     //   303: iinc #13, 1
/*     */     //   306: goto -> 248
/*     */     //   309: iconst_m1
/*     */     //   310: istore #10
/*     */     //   312: iload #10
/*     */     //   314: iconst_m1
/*     */     //   315: if_icmpne -> 329
/*     */     //   318: aload_0
/*     */     //   319: getfield futureTasks : Ljava/util/List;
/*     */     //   322: invokeinterface size : ()I
/*     */     //   327: istore #10
/*     */     //   329: aload_0
/*     */     //   330: getfield futureTasks : Ljava/util/List;
/*     */     //   333: iload #10
/*     */     //   335: aload_1
/*     */     //   336: invokeinterface add : (ILjava/lang/Object;)V
/*     */     //   341: iload #10
/*     */     //   343: ifne -> 350
/*     */     //   346: iconst_1
/*     */     //   347: goto -> 351
/*     */     //   350: iconst_0
/*     */     //   351: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #144	-> 6
/*     */     //   #146	-> 11
/*     */     //   #147	-> 25
/*     */     //   #150	-> 31
/*     */     //   #151	-> 43
/*     */     //   #152	-> 49
/*     */     //   #153	-> 59
/*     */     //   #228	-> 65
/*     */     //   #229	-> 80
/*     */     //   #153	-> 90
/*     */     //   #231	-> 103
/*     */     //   #154	-> 104
/*     */     //   #156	-> 106
/*     */     //   #158	-> 118
/*     */     //   #159	-> 124
/*     */     //   #232	-> 130
/*     */     //   #233	-> 145
/*     */     //   #160	-> 155
/*     */     //   #161	-> 189
/*     */     //   #160	-> 215
/*     */     //   #235	-> 226
/*     */     //   #165	-> 227
/*     */     //   #236	-> 236
/*     */     //   #237	-> 239
/*     */     //   #237	-> 248
/*     */     //   #238	-> 267
/*     */     //   #165	-> 277
/*     */     //   #239	-> 298
/*     */     //   #240	-> 303
/*     */     //   #237	-> 306
/*     */     //   #242	-> 309
/*     */     //   #165	-> 310
/*     */     //   #166	-> 312
/*     */     //   #167	-> 329
/*     */     //   #170	-> 341
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   90	2	12	$i$a$-taskLog-TaskQueue$scheduleAndDecide$1	I
/*     */     //   62	42	10	queue$iv	Lokhttp3/internal/concurrent/TaskQueue;
/*     */     //   65	39	11	$i$f$taskLog	I
/*     */     //   155	60	12	$i$a$-taskLog-TaskQueue$scheduleAndDecide$2	I
/*     */     //   127	100	10	queue$iv	Lokhttp3/internal/concurrent/TaskQueue;
/*     */     //   130	97	11	$i$f$taskLog	I
/*     */     //   274	21	16	it	Lokhttp3/internal/concurrent/Task;
/*     */     //   277	18	17	$i$a$-indexOfFirst-TaskQueue$scheduleAndDecide$insertAt$1	I
/*     */     //   267	39	15	item$iv	Ljava/lang/Object;
/*     */     //   239	71	13	index$iv	I
/*     */     //   233	77	11	$this$indexOfFirst$iv	Ljava/util/List;
/*     */     //   236	74	12	$i$f$indexOfFirst	I
/*     */     //   312	40	10	insertAt	I
/*     */     //   43	309	9	existingIndex	I
/*     */     //   31	321	7	executeNanoTime	J
/*     */     //   25	327	5	now	J
/*     */     //   0	352	0	this	Lokhttp3/internal/concurrent/TaskQueue;
/*     */     //   0	352	1	task	Lokhttp3/internal/concurrent/Task;
/*     */     //   0	352	2	delayNanos	J
/* 247 */     //   0	352	4	recurrence	Z } public final void shutdown() { Object $this$assertThreadDoesntHoldLock$iv = this; int $i$f$assertThreadDoesntHoldLock = 0; if (Util.assertionsEnabled && Thread.holdsLock($this$assertThreadDoesntHoldLock$iv)) {
/* 248 */       Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + $this$assertThreadDoesntHoldLock$iv);
/*     */     } 
/*     */     $this$assertThreadDoesntHoldLock$iv = this.taskRunner;
/*     */     $i$f$assertThreadDoesntHoldLock = 0;
/*     */     synchronized (false) {
/*     */       int $i$a$-synchronized-TaskQueue$shutdown$1 = 0;
/*     */       this.shutdown = true;
/*     */       if (cancelAllAndDecide$okhttp())
/*     */         this.taskRunner.kickCoordinator$okhttp(this); 
/*     */       Unit unit = Unit.INSTANCE;
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=$this$assertThreadDoesntHoldLock$iv} */
/*     */     }  }
/*     */ 
/*     */   
/*     */   public final boolean cancelAllAndDecide$okhttp() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield activeTask : Lokhttp3/internal/concurrent/Task;
/*     */     //   4: ifnull -> 26
/*     */     //   7: aload_0
/*     */     //   8: getfield activeTask : Lokhttp3/internal/concurrent/Task;
/*     */     //   11: dup
/*     */     //   12: invokestatic checkNotNull : (Ljava/lang/Object;)V
/*     */     //   15: invokevirtual getCancelable : ()Z
/*     */     //   18: ifeq -> 26
/*     */     //   21: aload_0
/*     */     //   22: iconst_1
/*     */     //   23: putfield cancelActiveTask : Z
/*     */     //   26: iconst_0
/*     */     //   27: istore_1
/*     */     //   28: aload_0
/*     */     //   29: getfield futureTasks : Ljava/util/List;
/*     */     //   32: invokeinterface size : ()I
/*     */     //   37: iconst_1
/*     */     //   38: isub
/*     */     //   39: istore_2
/*     */     //   40: iconst_0
/*     */     //   41: istore_3
/*     */     //   42: iload_2
/*     */     //   43: iflt -> 146
/*     */     //   46: aload_0
/*     */     //   47: getfield futureTasks : Ljava/util/List;
/*     */     //   50: iload_2
/*     */     //   51: invokeinterface get : (I)Ljava/lang/Object;
/*     */     //   56: checkcast okhttp3/internal/concurrent/Task
/*     */     //   59: invokevirtual getCancelable : ()Z
/*     */     //   62: ifeq -> 140
/*     */     //   65: aload_0
/*     */     //   66: getfield futureTasks : Ljava/util/List;
/*     */     //   69: iload_2
/*     */     //   70: invokeinterface get : (I)Ljava/lang/Object;
/*     */     //   75: checkcast okhttp3/internal/concurrent/Task
/*     */     //   78: astore #4
/*     */     //   80: aload_0
/*     */     //   81: astore #5
/*     */     //   83: iconst_0
/*     */     //   84: istore #6
/*     */     //   86: getstatic okhttp3/internal/concurrent/TaskRunner.Companion : Lokhttp3/internal/concurrent/TaskRunner$Companion;
/*     */     //   89: invokevirtual getLogger : ()Ljava/util/logging/Logger;
/*     */     //   92: getstatic java/util/logging/Level.FINE : Ljava/util/logging/Level;
/*     */     //   95: invokevirtual isLoggable : (Ljava/util/logging/Level;)Z
/*     */     //   98: ifeq -> 126
/*     */     //   101: aload #4
/*     */     //   103: aload #5
/*     */     //   105: astore #9
/*     */     //   107: astore #8
/*     */     //   109: iconst_0
/*     */     //   110: istore #7
/*     */     //   112: ldc_w 'canceled'
/*     */     //   115: astore #10
/*     */     //   117: aload #8
/*     */     //   119: aload #9
/*     */     //   121: aload #10
/*     */     //   123: invokestatic access$log : (Lokhttp3/internal/concurrent/Task;Lokhttp3/internal/concurrent/TaskQueue;Ljava/lang/String;)V
/*     */     //   126: nop
/*     */     //   127: iconst_1
/*     */     //   128: istore_1
/*     */     //   129: aload_0
/*     */     //   130: getfield futureTasks : Ljava/util/List;
/*     */     //   133: iload_2
/*     */     //   134: invokeinterface remove : (I)Ljava/lang/Object;
/*     */     //   139: pop
/*     */     //   140: iinc #2, -1
/*     */     //   143: goto -> 42
/*     */     //   146: iload_1
/*     */     //   147: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #201	-> 0
/*     */     //   #202	-> 21
/*     */     //   #205	-> 26
/*     */     //   #206	-> 28
/*     */     //   #206	-> 42
/*     */     //   #207	-> 46
/*     */     //   #208	-> 65
/*     */     //   #251	-> 86
/*     */     //   #252	-> 101
/*     */     //   #208	-> 112
/*     */     //   #254	-> 126
/*     */     //   #209	-> 127
/*     */     //   #210	-> 129
/*     */     //   #206	-> 140
/*     */     //   #213	-> 146
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   112	3	7	$i$a$-taskLog-TaskQueue$cancelAllAndDecide$1	I
/*     */     //   83	44	4	task$iv	Lokhttp3/internal/concurrent/Task;
/*     */     //   83	44	5	queue$iv	Lokhttp3/internal/concurrent/TaskQueue;
/*     */     //   86	41	6	$i$f$taskLog	I
/*     */     //   46	97	2	i	I
/*     */     //   28	120	1	tasksCanceled	Z
/*     */     //   0	148	0	this	Lokhttp3/internal/concurrent/TaskQueue;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String toString() {
/*     */     return this.name;
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/concurrent/TaskQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */