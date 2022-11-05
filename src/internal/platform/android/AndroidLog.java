/*     */ package okhttp3.internal.platform.android;
/*     */ 
/*     */ import android.util.Log;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.CopyOnWriteArraySet;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.collections.MapsKt;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.OkHttpClient;
/*     */ import okhttp3.internal.SuppressSignatureCheck;
/*     */ import okhttp3.internal.concurrent.TaskRunner;
/*     */ import okhttp3.internal.http2.Http2;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0006\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\000\n\002\030\002\n\002\030\002\n\000\n\002\020$\n\002\020\016\n\000\n\002\020\002\n\002\b\004\n\002\020\003\n\002\b\007\bÇ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J/\020\013\032\0020\f2\006\020\r\032\0020\n2\006\020\016\032\0020\0042\006\020\017\032\0020\n2\b\020\020\032\004\030\0010\021H\000¢\006\002\b\022J\006\020\023\032\0020\fJ\030\020\024\032\0020\f2\006\020\025\032\0020\n2\006\020\026\032\0020\nH\002J\020\020\027\032\0020\n2\006\020\r\032\0020\nH\002R\016\020\003\032\0020\004XT¢\006\002\n\000R\024\020\005\032\b\022\004\022\0020\0070\006X\004¢\006\002\n\000R\032\020\b\032\016\022\004\022\0020\n\022\004\022\0020\n0\tX\004¢\006\002\n\000¨\006\030"}, d2 = {"Lokhttp3/internal/platform/android/AndroidLog;", "", "()V", "MAX_LOG_LENGTH", "", "configuredLoggers", "Ljava/util/concurrent/CopyOnWriteArraySet;", "Ljava/util/logging/Logger;", "knownLoggers", "", "", "androidLog", "", "loggerName", "logLevel", "message", "t", "", "androidLog$okhttp", "enable", "enableLogging", "logger", "tag", "loggerTag", "okhttp"})
/*     */ @SuppressSignatureCheck
/*     */ public final class AndroidLog
/*     */ {
/*     */   private static final int MAX_LOG_LENGTH = 4000;
/*     */   
/*     */   static {
/*  50 */     AndroidLog androidLog = new AndroidLog();
/*     */   }
/*     */   
/*     */   private static final Map<String, String> knownLoggers;
/*  54 */   private static final CopyOnWriteArraySet<Logger> configuredLoggers = new CopyOnWriteArraySet<>(); public static final AndroidLog INSTANCE;
/*     */   static {
/*  56 */     LinkedHashMap<Object, Object> linkedHashMap1 = new LinkedHashMap<>(); boolean bool1 = false, bool2 = false; LinkedHashMap<Object, Object> $this$apply = linkedHashMap1; int $i$a$-apply-AndroidLog$knownLoggers$1 = 0;
/*  57 */     OkHttpClient.class.getPackage(); String packageName = (OkHttpClient.class.getPackage() != null) ? OkHttpClient.class.getPackage().getName() : null;
/*     */     
/*  59 */     if (packageName != null) {
/*  60 */       $this$apply.put(packageName, "OkHttp");
/*     */     }
/*     */     
/*  63 */     Intrinsics.checkNotNullExpressionValue(OkHttpClient.class.getName(), "OkHttpClient::class.java.name"); $this$apply.put(OkHttpClient.class.getName(), "okhttp.OkHttpClient");
/*  64 */     Intrinsics.checkNotNullExpressionValue(Http2.class.getName(), "Http2::class.java.name"); $this$apply.put(Http2.class.getName(), "okhttp.Http2");
/*  65 */     Intrinsics.checkNotNullExpressionValue(TaskRunner.class.getName(), "TaskRunner::class.java.name"); $this$apply.put(TaskRunner.class.getName(), "okhttp.TaskRunner");
/*  66 */     $this$apply.put("okhttp3.mockwebserver.MockWebServer", "okhttp.MockWebServer");
/*  67 */     knownLoggers = MapsKt.toMap(linkedHashMap1);
/*     */   }
/*     */   public final void androidLog$okhttp(@NotNull String loggerName, int logLevel, @NotNull String message, @Nullable Throwable t) {
/*  70 */     Intrinsics.checkNotNullParameter(loggerName, "loggerName"); Intrinsics.checkNotNullParameter(message, "message"); String tag = loggerTag(loggerName);
/*     */     
/*  72 */     if (Log.isLoggable(tag, logLevel)) {
/*  73 */       String logMessage = message;
/*  74 */       if (t != null) logMessage = logMessage + "\n" + Log.getStackTraceString(t);
/*     */ 
/*     */       
/*  77 */       int i = 0;
/*  78 */       int length = logMessage.length();
/*  79 */       while (i < length) {
/*  80 */         int newline = StringsKt.indexOf$default(logMessage, '\n', i, false, 4, null);
/*  81 */         newline = (newline != -1) ? newline : length;
/*     */         while (true) {
/*  83 */           int j = i + 4000; boolean bool = false; int end = Math.min(newline, j);
/*  84 */           String str = logMessage; bool = false; if (str == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str.substring(i, end), "(this as java.lang.Strin…ing(startIndex, endIndex)"); Log.println(logLevel, tag, str.substring(i, end));
/*  85 */           i = end;
/*  86 */           if (i >= newline) {
/*  87 */             i++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private final String loggerTag(String loggerName) {
/*  95 */     if ((String)knownLoggers.get(loggerName) == null) (String)knownLoggers.get(loggerName);  return StringsKt.take(loggerName, 23); } public final void enable() {
/*     */     Iterator<Map.Entry> iterator;
/*     */     Map<String, String> map;
/*     */     boolean bool;
/*  99 */     for (map = knownLoggers, bool = false, iterator = map.entrySet().iterator(); iterator.hasNext(); ) { Map.Entry entry1 = iterator.next(), entry2 = entry1; boolean bool1 = false; String str1 = (String)entry2.getKey(); entry2 = entry1; bool1 = false; String tag = (String)entry2.getValue();
/* 100 */       enableLogging(str1, tag); }
/*     */   
/*     */   }
/*     */   
/*     */   private final void enableLogging(String logger, String tag) {
/* 105 */     Logger logger1 = Logger.getLogger(logger);
/* 106 */     if (configuredLoggers.add(logger1)) {
/* 107 */       Intrinsics.checkNotNullExpressionValue(logger1, "logger"); logger1.setUseParentHandlers(false);
/*     */       
/* 109 */       logger1.setLevel(
/* 110 */           Log.isLoggable(tag, 3) ? Level.FINE : (
/* 111 */           Log.isLoggable(tag, 4) ? Level.INFO : 
/* 112 */           Level.WARNING));
/*     */       
/* 114 */       logger1.addHandler(AndroidLogHandler.INSTANCE);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/platform/android/AndroidLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */