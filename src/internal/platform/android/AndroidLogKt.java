/*    */ package okhttp3.internal.platform.android;
/*    */ 
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.LogRecord;
/*    */ import kotlin.Metadata;
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
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 2, d1 = {"\000\016\n\000\n\002\020\b\n\002\030\002\n\002\b\003\"\030\020\000\032\0020\001*\0020\0028BX\004¢\006\006\032\004\b\003\020\004¨\006\005"}, d2 = {"androidLevel", "", "Ljava/util/logging/LogRecord;", "getAndroidLevel", "(Ljava/util/logging/LogRecord;)I", "okhttp"})
/*    */ public final class AndroidLogKt
/*    */ {
/*    */   private static final int getAndroidLevel(LogRecord $this$androidLevel) {
/* 31 */     return 
/* 32 */       ($this$androidLevel.getLevel().intValue() > Level.INFO.intValue()) ? 5 : (
/* 33 */       ($this$androidLevel.getLevel().intValue() == Level.INFO.intValue()) ? 4 : 
/* 34 */       3);
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/platform/android/AndroidLogKt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */