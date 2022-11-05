/*    */ package okhttp3.internal.proxy;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.Proxy;
/*    */ import java.net.ProxySelector;
/*    */ import java.net.SocketAddress;
/*    */ import java.net.URI;
/*    */ import java.util.List;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.collections.CollectionsKt;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000.\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\000\bÆ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J&\020\003\032\0020\0042\b\020\005\032\004\030\0010\0062\b\020\007\032\004\030\0010\b2\b\020\t\032\004\030\0010\nH\026J\030\020\013\032\b\022\004\022\0020\r0\f2\b\020\005\032\004\030\0010\006H\026¨\006\016"}, d2 = {"Lokhttp3/internal/proxy/NullProxySelector;", "Ljava/net/ProxySelector;", "()V", "connectFailed", "", "uri", "Ljava/net/URI;", "sa", "Ljava/net/SocketAddress;", "ioe", "Ljava/io/IOException;", "select", "", "Ljava/net/Proxy;", "okhttp"})
/*    */ public final class NullProxySelector
/*    */   extends ProxySelector
/*    */ {
/*    */   public static final NullProxySelector INSTANCE;
/*    */   
/*    */   static {
/* 27 */     NullProxySelector nullProxySelector = new NullProxySelector(); } @NotNull
/*    */   public List<Proxy> select(@Nullable URI uri) {
/* 29 */     boolean bool1 = false, bool2 = false; if (uri == null) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 37 */       int $i$a$-requireNotNull-NullProxySelector$select$1 = 0;
/*    */       String str = "uri must not be null";
/*    */       throw (Throwable)new IllegalArgumentException(str.toString());
/*    */     } 
/*    */     return CollectionsKt.listOf(Proxy.NO_PROXY);
/*    */   }
/*    */   
/*    */   public void connectFailed(@Nullable URI uri, @Nullable SocketAddress sa, @Nullable IOException ioe) {}
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/proxy/NullProxySelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */