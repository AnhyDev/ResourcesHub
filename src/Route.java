/*    */ package okhttp3;
/*    */ 
/*    */ import java.net.InetSocketAddress;
/*    */ import java.net.Proxy;
/*    */ import kotlin.Deprecated;
/*    */ import kotlin.DeprecationLevel;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.ReplaceWith;
/*    */ import kotlin.jvm.JvmName;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0004\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\006\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\004\n\002\020\016\n\000\030\0002\0020\001B\035\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007¢\006\002\020\bJ\r\020\002\032\0020\003H\007¢\006\002\b\fJ\023\020\r\032\0020\0162\b\020\017\032\004\030\0010\001H\002J\b\020\020\032\0020\021H\026J\r\020\004\032\0020\005H\007¢\006\002\b\022J\006\020\023\032\0020\016J\r\020\006\032\0020\007H\007¢\006\002\b\024J\b\020\025\032\0020\026H\026R\023\020\002\032\0020\0038\007¢\006\b\n\000\032\004\b\002\020\tR\023\020\004\032\0020\0058\007¢\006\b\n\000\032\004\b\004\020\nR\023\020\006\032\0020\0078\007¢\006\b\n\000\032\004\b\006\020\013¨\006\027"}, d2 = {"Lokhttp3/Route;", "", "address", "Lokhttp3/Address;", "proxy", "Ljava/net/Proxy;", "socketAddress", "Ljava/net/InetSocketAddress;", "(Lokhttp3/Address;Ljava/net/Proxy;Ljava/net/InetSocketAddress;)V", "()Lokhttp3/Address;", "()Ljava/net/Proxy;", "()Ljava/net/InetSocketAddress;", "-deprecated_address", "equals", "", "other", "hashCode", "", "-deprecated_proxy", "requiresTunnel", "-deprecated_socketAddress", "toString", "", "okhttp"})
/*    */ public final class Route
/*    */ {
/*    */   @NotNull
/*    */   private final Address address;
/*    */   @NotNull
/*    */   private final Proxy proxy;
/*    */   @NotNull
/*    */   private final InetSocketAddress socketAddress;
/*    */   
/*    */   public Route(@NotNull Address address, @NotNull Proxy proxy, @NotNull InetSocketAddress socketAddress) {
/* 34 */     this.address = address; this.proxy = proxy; this.socketAddress = socketAddress; } @JvmName(name = "address") @NotNull
/* 35 */   public final Address address() { return this.address; }
/*    */ 
/*    */ 
/*    */   
/*    */   @JvmName(name = "proxy")
/*    */   @NotNull
/*    */   public final Proxy proxy() {
/* 42 */     return this.proxy; } @JvmName(name = "socketAddress") @NotNull
/* 43 */   public final InetSocketAddress socketAddress() { return this.socketAddress; }
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "address"), level = DeprecationLevel.ERROR)
/*    */   @JvmName(name = "-deprecated_address")
/*    */   @NotNull
/*    */   public final Address -deprecated_address() {
/* 51 */     return this.address;
/*    */   }
/*    */   
/*    */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "proxy"), level = DeprecationLevel.ERROR)
/*    */   @JvmName(name = "-deprecated_proxy")
/*    */   @NotNull
/*    */   public final Proxy -deprecated_proxy() {
/* 58 */     return this.proxy;
/*    */   }
/*    */   
/*    */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "socketAddress"), level = DeprecationLevel.ERROR)
/*    */   @JvmName(name = "-deprecated_socketAddress")
/*    */   @NotNull
/*    */   public final InetSocketAddress -deprecated_socketAddress() {
/* 65 */     return this.socketAddress;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final boolean requiresTunnel() {
/* 73 */     return (this.address.sslSocketFactory() != null && this.proxy.type() == Proxy.Type.HTTP);
/*    */   }
/*    */   public boolean equals(@Nullable Object other) {
/* 76 */     return (other instanceof Route && Intrinsics.areEqual(((Route)other).address, this.address) && Intrinsics.areEqual(((Route)other).proxy, this.proxy) && Intrinsics.areEqual(((Route)other).socketAddress, this.socketAddress));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 83 */     int result = 17;
/* 84 */     result = 31 * result + this.address.hashCode();
/* 85 */     result = 31 * result + this.proxy.hashCode();
/* 86 */     result = 31 * result + this.socketAddress.hashCode();
/* 87 */     return result;
/*    */   } @NotNull
/*    */   public String toString() {
/* 90 */     return "Route{" + this.socketAddress + '}';
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/Route.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */