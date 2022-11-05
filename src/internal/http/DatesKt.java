/*     */ package okhttp3.internal.http;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParsePosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.Unit;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.internal.Util;
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
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 2, d1 = {"\000+\n\000\n\002\020\021\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\002\n\002\020\t\n\000\n\002\b\003\n\002\030\002\n\002\b\002*\001\n\032\016\020\f\032\004\030\0010\r*\0020\005H\000\032\f\020\016\032\0020\005*\0020\rH\000\"\030\020\000\032\n\022\006\022\004\030\0010\0020\001X\004¢\006\004\n\002\020\003\"\026\020\004\032\b\022\004\022\0020\0050\001X\004¢\006\004\n\002\020\006\"\016\020\007\032\0020\bXT¢\006\002\n\000\"\020\020\t\032\0020\nX\004¢\006\004\n\002\020\013¨\006\017"}, d2 = {"BROWSER_COMPATIBLE_DATE_FORMATS", "", "Ljava/text/DateFormat;", "[Ljava/text/DateFormat;", "BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS", "", "[Ljava/lang/String;", "MAX_DATE", "", "STANDARD_DATE_FORMAT", "okhttp3/internal/http/DatesKt$STANDARD_DATE_FORMAT$1", "Lokhttp3/internal/http/DatesKt$STANDARD_DATE_FORMAT$1;", "toHttpDateOrNull", "Ljava/util/Date;", "toHttpDateString", "okhttp"})
/*     */ public final class DatesKt
/*     */ {
/*     */   public static final long MAX_DATE = 253402300799999L;
/*  32 */   private static final DatesKt$STANDARD_DATE_FORMAT$1 STANDARD_DATE_FORMAT = new DatesKt$STANDARD_DATE_FORMAT$1(); @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\021\n\000\n\002\030\002\n\002\030\002\n\002\b\002*\001\000\b\n\030\0002\b\022\004\022\0020\0020\001J\b\020\003\032\0020\002H\024¨\006\004"}, d2 = {"okhttp3/internal/http/DatesKt$STANDARD_DATE_FORMAT$1", "Ljava/lang/ThreadLocal;", "Ljava/text/DateFormat;", "initialValue", "okhttp"})
/*     */   public static final class DatesKt$STANDARD_DATE_FORMAT$1 extends ThreadLocal<DateFormat> { @NotNull
/*     */     protected DateFormat initialValue() {
/*  35 */       SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US); boolean bool1 = false, bool2 = false; SimpleDateFormat $this$apply = simpleDateFormat1; int $i$a$-apply-DatesKt$STANDARD_DATE_FORMAT$1$initialValue$1 = 0;
/*  36 */       $this$apply.setLenient(false);
/*  37 */       $this$apply.setTimeZone(Util.UTC);
/*     */       return simpleDateFormat1;
/*     */     } }
/*     */ 
/*     */ 
/*     */   
/*  43 */   private static final String[] BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS = new String[] {
/*     */       
/*  45 */       "EEE, dd MMM yyyy HH:mm:ss zzz", 
/*  46 */       "EEEE, dd-MMM-yy HH:mm:ss zzz", 
/*  47 */       "EEE MMM d HH:mm:ss yyyy", 
/*     */       
/*  49 */       "EEE, dd-MMM-yyyy HH:mm:ss z", 
/*  50 */       "EEE, dd-MMM-yyyy HH-mm-ss z", 
/*  51 */       "EEE, dd MMM yy HH:mm:ss z", 
/*  52 */       "EEE dd-MMM-yyyy HH:mm:ss z", 
/*  53 */       "EEE dd MMM yyyy HH:mm:ss z", 
/*  54 */       "EEE dd-MMM-yyyy HH-mm-ss z", 
/*  55 */       "EEE dd-MMM-yy HH:mm:ss z", 
/*  56 */       "EEE dd MMM yy HH:mm:ss z", 
/*  57 */       "EEE,dd-MMM-yy HH:mm:ss z", 
/*  58 */       "EEE,dd-MMM-yyyy HH:mm:ss z", 
/*  59 */       "EEE, dd-MM-yyyy HH:mm:ss z", 
/*     */ 
/*     */       
/*  62 */       "EEE MMM d yyyy HH:mm:ss z"
/*     */     };
/*     */ 
/*     */   
/*  66 */   private static final DateFormat[] BROWSER_COMPATIBLE_DATE_FORMATS = new DateFormat[BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS.length];
/*     */   
/*     */   @Nullable
/*     */   public static final Date toHttpDateOrNull(@NotNull String $this$toHttpDateOrNull) {
/*  70 */     Intrinsics.checkNotNullParameter($this$toHttpDateOrNull, "$this$toHttpDateOrNull"); CharSequence charSequence = $this$toHttpDateOrNull; boolean bool1 = false; if ((charSequence.length() == 0)) return null;
/*     */     
/*  72 */     ParsePosition position = new ParsePosition(0);
/*  73 */     Object result = STANDARD_DATE_FORMAT.get().parse($this$toHttpDateOrNull, position);
/*  74 */     if (position.getIndex() == $this$toHttpDateOrNull.length())
/*     */     {
/*     */       
/*  77 */       return (Date)result;
/*     */     }
/*  79 */     String[] arrayOfString = BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS; boolean bool2 = false; synchronized (false) { int $i$a$-synchronized-DatesKt$toHttpDateOrNull$1 = 0; byte b; int i;
/*  80 */       for (b = 0, i = BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS.length; b < i; b++) {
/*  81 */         DateFormat format = BROWSER_COMPATIBLE_DATE_FORMATS[b];
/*  82 */         if (format == null) {
/*  83 */           SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS[b], Locale.US); boolean bool3 = false, bool4 = false; SimpleDateFormat $this$apply = simpleDateFormat1; int $i$a$-apply-DatesKt$toHttpDateOrNull$1$1 = 0;
/*     */ 
/*     */           
/*  86 */           $this$apply.setTimeZone(Util.UTC);
/*     */           format = simpleDateFormat1;
/*  88 */           BROWSER_COMPATIBLE_DATE_FORMATS[b] = format;
/*     */         } 
/*  90 */         position.setIndex(0);
/*  91 */         result = format.parse($this$toHttpDateOrNull, position);
/*  92 */         if (position.getIndex() != 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  98 */           Object object = result; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/String, dimension=1}, name=null} */ return (Date)object;
/*     */         } 
/*     */       } 
/* 101 */       Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/String, dimension=1}, name=null} */ }
/* 102 */      return null;
/*     */   }
/*     */   @NotNull
/*     */   public static final String toHttpDateString(@NotNull Date $this$toHttpDateString) {
/* 106 */     Intrinsics.checkNotNullParameter($this$toHttpDateString, "$this$toHttpDateString"); Intrinsics.checkNotNullExpressionValue(STANDARD_DATE_FORMAT.get().format($this$toHttpDateString), "STANDARD_DATE_FORMAT.get().format(this)"); return STANDARD_DATE_FORMAT.get().format($this$toHttpDateString);
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http/DatesKt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */