/*     */ package okhttp3;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import kotlin.Deprecated;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.JvmStatic;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\022\n\002\030\002\n\002\020\020\n\000\n\002\020\016\n\002\b\n\b\001\030\000 \f2\b\022\004\022\0020\0000\001:\001\fB\017\b\002\022\006\020\002\032\0020\003¢\006\002\020\004J\b\020\005\032\0020\003H\026R\016\020\002\032\0020\003X\004¢\006\002\n\000j\002\b\006j\002\b\007j\002\b\bj\002\b\tj\002\b\nj\002\b\013¨\006\r"}, d2 = {"Lokhttp3/Protocol;", "", "protocol", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toString", "HTTP_1_0", "HTTP_1_1", "SPDY_3", "HTTP_2", "H2_PRIOR_KNOWLEDGE", "QUIC", "Companion", "okhttp"})
/*     */ public enum Protocol
/*     */ {
/*     */   HTTP_1_0, HTTP_1_1, SPDY_3, HTTP_2, H2_PRIOR_KNOWLEDGE, QUIC;
/*     */   
/*     */   @JvmStatic
/*     */   @NotNull
/*     */   public static final Protocol get(@NotNull String protocol) throws IOException {
/*     */     return Companion.get(protocol);
/*     */   }
/*     */   
/*     */   Protocol(String protocol) {
/*  31 */     this.protocol = protocol;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final String protocol;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public static final Companion Companion = new Companion(null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String toString() {
/*  94 */     return this.protocol;
/*     */   }
/*     */ 
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\030\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\020\020\003\032\0020\0042\006\020\005\032\0020\006H\007¨\006\007"}, d2 = {"Lokhttp3/Protocol$Companion;", "", "()V", "get", "Lokhttp3/Protocol;", "protocol", "", "okhttp"})
/*     */   public static final class Companion
/*     */   {
/*     */     private Companion() {}
/*     */     
/*     */     @JvmStatic
/*     */     @NotNull
/*     */     public final Protocol get(@NotNull String protocol) throws IOException {
/* 106 */       Intrinsics.checkNotNullParameter(protocol, "protocol");
/* 107 */       String str = protocol;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 113 */       if (Intrinsics.areEqual(str, Protocol.QUIC.protocol)) {  }
/* 114 */       else { throw (Throwable)new IOException("Unexpected protocol: " + protocol); }
/*     */       
/*     */       return Intrinsics.areEqual(str, Protocol.HTTP_1_0.protocol) ? Protocol.HTTP_1_0 : (Intrinsics.areEqual(str, Protocol.HTTP_1_1.protocol) ? Protocol.HTTP_1_1 : (Intrinsics.areEqual(str, Protocol.H2_PRIOR_KNOWLEDGE.protocol) ? Protocol.H2_PRIOR_KNOWLEDGE : (Intrinsics.areEqual(str, Protocol.HTTP_2.protocol) ? Protocol.HTTP_2 : (Intrinsics.areEqual(str, Protocol.SPDY_3.protocol) ? Protocol.SPDY_3 : (Protocol)"JD-Core does not support Kotlin"))));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/Protocol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */