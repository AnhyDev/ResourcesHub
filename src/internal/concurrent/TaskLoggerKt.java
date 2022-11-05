/*    */ package okhttp3.internal.concurrent;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.logging.Level;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.functions.Function0;
/*    */ import kotlin.jvm.internal.InlineMarker;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import kotlin.jvm.internal.StringCompanionObject;
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
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 2, d1 = {"\000*\n\000\n\002\020\016\n\000\n\002\020\t\n\000\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\004\032\016\020\000\032\0020\0012\006\020\002\032\0020\003\032 \020\004\032\0020\0052\006\020\006\032\0020\0072\006\020\b\032\0020\t2\006\020\n\032\0020\001H\002\0325\020\013\032\002H\f\"\004\b\000\020\f2\006\020\006\032\0020\0072\006\020\b\032\0020\t2\f\020\r\032\b\022\004\022\002H\f0\016H\bø\001\000¢\006\002\020\017\032*\020\020\032\0020\0052\006\020\006\032\0020\0072\006\020\b\032\0020\t2\f\020\021\032\b\022\004\022\0020\0010\016H\bø\001\000\002\007\n\005\b20\001¨\006\022"}, d2 = {"formatDuration", "", "ns", "", "log", "", "task", "Lokhttp3/internal/concurrent/Task;", "queue", "Lokhttp3/internal/concurrent/TaskQueue;", "message", "logElapsed", "T", "block", "Lkotlin/Function0;", "(Lokhttp3/internal/concurrent/Task;Lokhttp3/internal/concurrent/TaskQueue;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "taskLog", "messageBlock", "okhttp"})
/*    */ public final class TaskLoggerKt
/*    */ {
/*    */   public static final void taskLog(@NotNull Task task, @NotNull TaskQueue queue, @NotNull Function0 messageBlock) {
/* 25 */     int $i$f$taskLog = 0; Intrinsics.checkNotNullParameter(task, "task"); Intrinsics.checkNotNullParameter(queue, "queue"); Intrinsics.checkNotNullParameter(messageBlock, "messageBlock"); if (TaskRunner.Companion.getLogger().isLoggable(Level.FINE)) {
/* 26 */       access$log(task, queue, (String)messageBlock.invoke());
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static final <T> T logElapsed(@NotNull Task task, @NotNull TaskQueue queue, @NotNull Function0 block) {
/*    */     Object object;
/* 35 */     int $i$f$logElapsed = 0; Intrinsics.checkNotNullParameter(task, "task"); Intrinsics.checkNotNullParameter(queue, "queue"); Intrinsics.checkNotNullParameter(block, "block"); long startNs = -1L;
/* 36 */     boolean loggingEnabled = TaskRunner.Companion.getLogger().isLoggable(Level.FINE);
/* 37 */     if (loggingEnabled) {
/* 38 */       startNs = queue.getTaskRunner$okhttp().getBackend().nanoTime();
/* 39 */       access$log(task, queue, "starting");
/*    */     } 
/*    */     
/* 42 */     boolean completedNormally = false;
/*    */     try {
/* 44 */       object = block.invoke();
/* 45 */       completedNormally = true;
/* 46 */       Object object1 = object; InlineMarker.finallyStart(1);
/*    */       
/* 48 */       if (loggingEnabled) {
/* 49 */         long elapsedNs = queue.getTaskRunner$okhttp().getBackend().nanoTime() - startNs;
/*    */         
/* 51 */         access$log(task, queue, "finished run in " + formatDuration(elapsedNs));
/*    */       } 
/*    */       
/* 54 */       return (T)object1;
/*    */     } finally {
/* 56 */       object = null;
/*    */     } 
/*    */     InlineMarker.finallyEnd(1);
/*    */     throw object; } private static final void log(Task task, TaskQueue queue, String message) {
/* 60 */     StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE; String str = "%-22s"; Object[] arrayOfObject = { message }; boolean bool = false; Intrinsics.checkNotNullExpressionValue(String.format(str, Arrays.copyOf(arrayOfObject, arrayOfObject.length)), "java.lang.String.format(format, *args)"); TaskRunner.Companion.getLogger().fine(queue.getName$okhttp() + ' ' + String.format(str, Arrays.copyOf(arrayOfObject, arrayOfObject.length)) + ": " + task.getName());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public static final String formatDuration(long ns) {
/* 72 */     String s = 
/* 73 */       (ns <= -999500000L) ? (((ns - 500000000L) / 1000000000L) + " s ") : (
/* 74 */       (ns <= -999500L) ? (((ns - 500000L) / 1000000L) + " ms") : (
/* 75 */       (ns <= 0L) ? (((ns - 500L) / 1000L) + " µs") : (
/* 76 */       (ns < 999500L) ? (((ns + 500L) / 1000L) + " µs") : (
/* 77 */       (ns < 999500000L) ? (((ns + 500000L) / 1000000L) + " ms") : (((
/* 78 */       ns + 500000000L) / 1000000000L) + " s ")))));
/*    */     
/* 80 */     StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE; String str1 = "%6s"; Object[] arrayOfObject = { s }; boolean bool = false; Intrinsics.checkNotNullExpressionValue(String.format(str1, Arrays.copyOf(arrayOfObject, arrayOfObject.length)), "java.lang.String.format(format, *args)"); return String.format(str1, Arrays.copyOf(arrayOfObject, arrayOfObject.length));
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/concurrent/TaskLoggerKt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */