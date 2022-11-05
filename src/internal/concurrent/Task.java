/*    */ package okhttp3.internal.concurrent;
/*    */ 
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*    */ import kotlin.jvm.internal.Intrinsics;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0000\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\000\n\002\020\013\n\002\b\006\n\002\020\t\n\002\b\005\n\002\030\002\n\002\b\005\n\002\020\002\n\002\b\004\b&\030\0002\0020\001B\027\022\006\020\002\032\0020\003\022\b\b\002\020\004\032\0020\005¢\006\002\020\006J\025\020\027\032\0020\0302\006\020\021\032\0020\022H\000¢\006\002\b\031J\b\020\032\032\0020\fH&J\b\020\033\032\0020\003H\026R\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\007\020\bR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\t\020\nR\032\020\013\032\0020\fX\016¢\006\016\n\000\032\004\b\r\020\016\"\004\b\017\020\020R\034\020\021\032\004\030\0010\022X\016¢\006\016\n\000\032\004\b\023\020\024\"\004\b\025\020\026¨\006\034"}, d2 = {"Lokhttp3/internal/concurrent/Task;", "", "name", "", "cancelable", "", "(Ljava/lang/String;Z)V", "getCancelable", "()Z", "getName", "()Ljava/lang/String;", "nextExecuteNanoTime", "", "getNextExecuteNanoTime$okhttp", "()J", "setNextExecuteNanoTime$okhttp", "(J)V", "queue", "Lokhttp3/internal/concurrent/TaskQueue;", "getQueue$okhttp", "()Lokhttp3/internal/concurrent/TaskQueue;", "setQueue$okhttp", "(Lokhttp3/internal/concurrent/TaskQueue;)V", "initQueue", "", "initQueue$okhttp", "runOnce", "toString", "okhttp"})
/*    */ public abstract class Task
/*    */ {
/*    */   @Nullable
/*    */   private TaskQueue queue;
/*    */   private long nextExecuteNanoTime;
/*    */   @NotNull
/*    */   private final String name;
/*    */   private final boolean cancelable;
/*    */   
/*    */   @NotNull
/*    */   public final String getName() {
/*    */     return this.name;
/*    */   }
/*    */   
/*    */   public final boolean getCancelable() {
/*    */     return this.cancelable;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public final TaskQueue getQueue$okhttp() {
/*    */     return this.queue;
/*    */   }
/*    */   
/*    */   public final void setQueue$okhttp(@Nullable TaskQueue <set-?>) {
/*    */     this.queue = <set-?>;
/*    */   }
/*    */   
/*    */   public Task(@NotNull String name, boolean cancelable) {
/* 49 */     this.name = name; this.cancelable = cancelable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 57 */     this.nextExecuteNanoTime = -1L; } public final long getNextExecuteNanoTime$okhttp() { return this.nextExecuteNanoTime; } public final void setNextExecuteNanoTime$okhttp(long <set-?>) { this.nextExecuteNanoTime = <set-?>; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final void initQueue$okhttp(@NotNull TaskQueue queue) {
/* 63 */     Intrinsics.checkNotNullParameter(queue, "queue"); if (this.queue == queue)
/*    */       return; 
/* 65 */     boolean bool1 = (this.queue == null) ? true : false, bool2 = false, bool3 = false; if (!bool1) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 72 */       int $i$a$-check-Task$initQueue$1 = 0;
/*    */       String str = "task is in multiple queues";
/*    */       throw (Throwable)new IllegalStateException(str.toString());
/*    */     } 
/*    */     this.queue = queue;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public String toString() {
/*    */     return this.name;
/*    */   }
/*    */   
/*    */   public abstract long runOnce();
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/concurrent/Task.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */