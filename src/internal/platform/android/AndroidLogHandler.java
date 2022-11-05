/*    */ package okhttp3.internal.platform.android;
/*    */ 
/*    */ import java.util.logging.Handler;
/*    */ import java.util.logging.LogRecord;
/*    */ import kotlin.Metadata;
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
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\032\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\003\n\002\030\002\n\000\bÆ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J\b\020\003\032\0020\004H\026J\b\020\005\032\0020\004H\026J\020\020\006\032\0020\0042\006\020\007\032\0020\bH\026¨\006\t"}, d2 = {"Lokhttp3/internal/platform/android/AndroidLogHandler;", "Ljava/util/logging/Handler;", "()V", "close", "", "flush", "publish", "record", "Ljava/util/logging/LogRecord;", "okhttp"})
/*    */ public final class AndroidLogHandler
/*    */   extends Handler
/*    */ {
/*    */   public static final AndroidLogHandler INSTANCE;
/*    */   
/*    */   static {
/* 37 */     AndroidLogHandler androidLogHandler = new AndroidLogHandler();
/*    */   } public void publish(@NotNull LogRecord record) {
/* 39 */     Intrinsics.checkNotNullParameter(record, "record"); Intrinsics.checkNotNullExpressionValue(record.getLoggerName(), "record.loggerName"); Intrinsics.checkNotNullExpressionValue(record.getMessage(), "record.message"); AndroidLog.INSTANCE.androidLog$okhttp(record.getLoggerName(), AndroidLogKt.access$getAndroidLevel$p(record), record.getMessage(), record.getThrown());
/*    */   }
/*    */   
/*    */   public void flush() {}
/*    */   
/*    */   public void close() {}
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/platform/android/AndroidLogHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */