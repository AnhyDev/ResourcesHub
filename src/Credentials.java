/*    */ package okhttp3;
/*    */ 
/*    */ import java.nio.charset.Charset;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.JvmOverloads;
/*    */ import kotlin.jvm.JvmStatic;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import okio.ByteString;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\003\n\002\030\002\n\000\bÆ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J\"\020\003\032\0020\0042\006\020\005\032\0020\0042\006\020\006\032\0020\0042\b\b\002\020\007\032\0020\bH\007¨\006\t"}, d2 = {"Lokhttp3/Credentials;", "", "()V", "basic", "", "username", "password", "charset", "Ljava/nio/charset/Charset;", "okhttp"})
/*    */ public final class Credentials
/*    */ {
/*    */   public static final Credentials INSTANCE;
/*    */   
/*    */   static {
/* 23 */     Credentials credentials = new Credentials();
/*    */   }
/*    */   
/*    */   @JvmStatic
/*    */   @JvmOverloads
/*    */   @NotNull
/*    */   public static final String basic(@NotNull String username, @NotNull String password, @NotNull Charset charset) {
/* 30 */     Intrinsics.checkNotNullParameter(username, "username"); Intrinsics.checkNotNullParameter(password, "password"); Intrinsics.checkNotNullParameter(charset, "charset"); String usernameAndPassword = username + ':' + password;
/* 31 */     String encoded = ByteString.Companion.encodeString(usernameAndPassword, charset).base64();
/* 32 */     return "Basic " + encoded;
/*    */   }
/*    */   
/*    */   @JvmStatic
/*    */   @JvmOverloads
/*    */   @NotNull
/*    */   public static final String basic(@NotNull String username, @NotNull String password) {
/*    */     return basic$default(username, password, null, 4, null);
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/Credentials.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */