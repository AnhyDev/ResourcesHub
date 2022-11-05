/*    */ package okhttp3.internal.platform.android;
/*    */ 
/*    */ import java.lang.reflect.Method;
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
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000 \n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\005\n\002\020\016\n\000\n\002\020\013\n\002\b\003\b\000\030\000 \r2\0020\001:\001\rB#\022\b\020\002\032\004\030\0010\003\022\b\020\004\032\004\030\0010\003\022\b\020\005\032\004\030\0010\003¢\006\002\020\006J\020\020\007\032\004\030\0010\0012\006\020\b\032\0020\tJ\020\020\n\032\0020\0132\b\020\f\032\004\030\0010\001R\020\020\002\032\004\030\0010\003X\004¢\006\002\n\000R\020\020\004\032\004\030\0010\003X\004¢\006\002\n\000R\020\020\005\032\004\030\0010\003X\004¢\006\002\n\000¨\006\016"}, d2 = {"Lokhttp3/internal/platform/android/CloseGuard;", "", "getMethod", "Ljava/lang/reflect/Method;", "openMethod", "warnIfOpenMethod", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "createAndOpen", "closer", "", "warnIfOpen", "", "closeGuardInstance", "Companion", "okhttp"})
/*    */ public final class CloseGuard
/*    */ {
/*    */   private final Method getMethod;
/*    */   private final Method openMethod;
/*    */   private final Method warnIfOpenMethod;
/*    */   public static final Companion Companion = new Companion(null);
/*    */   
/*    */   public CloseGuard(@Nullable Method getMethod, @Nullable Method openMethod, @Nullable Method warnIfOpenMethod) {
/* 25 */     this.getMethod = getMethod; this.openMethod = openMethod; this.warnIfOpenMethod = warnIfOpenMethod;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public final Object createAndOpen(@NotNull String closer) {
/* 32 */     Intrinsics.checkNotNullParameter(closer, "closer"); if (this.getMethod != null) {
/*    */       try {
/* 34 */         Object closeGuardInstance = this.getMethod.invoke(null, new Object[0]);
/* 35 */         Intrinsics.checkNotNull(this.openMethod); this.openMethod.invoke(closeGuardInstance, new Object[] { closer });
/* 36 */         return closeGuardInstance;
/* 37 */       } catch (Exception exception) {}
/*    */     }
/*    */     
/* 40 */     return null;
/*    */   }
/*    */   
/*    */   public final boolean warnIfOpen(@Nullable Object closeGuardInstance) {
/* 44 */     boolean reported = false;
/* 45 */     if (closeGuardInstance != null) {
/*    */       try {
/* 47 */         Intrinsics.checkNotNull(this.warnIfOpenMethod); this.warnIfOpenMethod.invoke(closeGuardInstance, new Object[0]);
/* 48 */         reported = true;
/* 49 */       } catch (Exception exception) {}
/*    */     }
/*    */     
/* 52 */     return reported;
/*    */   }
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\022\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\006\020\003\032\0020\004¨\006\005"}, d2 = {"Lokhttp3/internal/platform/android/CloseGuard$Companion;", "", "()V", "get", "Lokhttp3/internal/platform/android/CloseGuard;", "okhttp"})
/*    */   public static final class Companion { @NotNull
/*    */     public final CloseGuard get() {
/* 57 */       Method getMethod = null;
/* 58 */       Method openMethod = null;
/* 59 */       Method warnIfOpenMethod = null;
/*    */       
/*    */       try {
/* 62 */         Class<?> closeGuardClass = Class.forName("dalvik.system.CloseGuard");
/* 63 */         getMethod = closeGuardClass.getMethod("get", new Class[0]);
/* 64 */         openMethod = closeGuardClass.getMethod("open", new Class[] { String.class });
/* 65 */         warnIfOpenMethod = closeGuardClass.getMethod("warnIfOpen", new Class[0]);
/* 66 */       } catch (Exception _) {
/* 67 */         getMethod = (Method)null;
/* 68 */         openMethod = (Method)null;
/* 69 */         warnIfOpenMethod = (Method)null;
/*    */       } 
/*    */       
/* 72 */       return new CloseGuard(getMethod, openMethod, warnIfOpenMethod);
/*    */     }
/*    */     
/*    */     private Companion() {} }
/*    */ 
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/platform/android/CloseGuard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */