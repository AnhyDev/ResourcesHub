/*    */ package okhttp3;
/*    */ 
/*    */ import java.net.InetAddress;
/*    */ import java.net.UnknownHostException;
/*    */ import java.util.List;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.collections.ArraysKt;
/*    */ import kotlin.jvm.JvmField;
/*    */ import kotlin.jvm.internal.DefaultConstructorMarker;
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
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\000\n\002\020 \n\002\030\002\n\000\n\002\020\016\n\002\b\002\bf\030\000 \0072\0020\001:\001\007J\026\020\002\032\b\022\004\022\0020\0040\0032\006\020\005\032\0020\006H&¨\006\b"}, d2 = {"Lokhttp3/Dns;", "", "lookup", "", "Ljava/net/InetAddress;", "hostname", "", "Companion", "okhttp"})
/*    */ public interface Dns
/*    */ {
/*    */   @JvmField
/*    */   @NotNull
/*    */   public static final Dns SYSTEM;
/*    */   
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\b\003\030\0002\0020\001:\001\005B\007\b\002¢\006\002\020\002R\026\020\003\032\0020\0048\006X\004ø\001\000¢\006\002\n\000¨\006\001\002\007\n\005\bF0\001¨\006\006"}, d2 = {"Lokhttp3/Dns$Companion;", "", "()V", "SYSTEM", "Lokhttp3/Dns;", "DnsSystem", "okhttp"})
/*    */   public static final class Companion
/*    */   {
/*    */     private Companion() {}
/*    */     
/*    */     @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\034\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020 \n\002\030\002\n\000\n\002\020\016\n\000\b\002\030\0002\0020\001B\005¢\006\002\020\002J\026\020\003\032\b\022\004\022\0020\0050\0042\006\020\006\032\0020\007H\026¨\006\b"}, d2 = {"Lokhttp3/Dns$Companion$DnsSystem;", "Lokhttp3/Dns;", "()V", "lookup", "", "Ljava/net/InetAddress;", "hostname", "", "okhttp"})
/*    */     private static final class DnsSystem
/*    */       implements Dns
/*    */     {
/*    */       @NotNull
/*    */       public List<InetAddress> lookup(@NotNull String hostname) {
/* 48 */         Intrinsics.checkNotNullParameter(hostname, "hostname"); try {
/* 49 */           Intrinsics.checkNotNullExpressionValue(InetAddress.getAllByName(hostname), "InetAddress.getAllByName(hostname)"); return ArraysKt.toList((Object[])InetAddress.getAllByName(hostname));
/* 50 */         } catch (NullPointerException e) {
/* 51 */           UnknownHostException unknownHostException1 = new UnknownHostException("Broken system behaviour for dns lookup of " + hostname); boolean bool1 = false, bool2 = false; UnknownHostException $this$apply = unknownHostException1; int $i$a$-apply-Dns$Companion$DnsSystem$lookup$1 = 0;
/* 52 */           $this$apply.initCause(e);
/*    */           throw (Throwable)unknownHostException1;
/*    */         } 
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static final Companion Companion = new Companion(null);
/*    */   
/*    */   @NotNull
/*    */   List<InetAddress> lookup(@NotNull String paramString) throws UnknownHostException;
/*    */   
/*    */   static {
/*    */     SYSTEM = new Companion.DnsSystem();
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/Dns.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */