/*     */ package okhttp3;
/*     */ 
/*     */ import kotlin.Deprecated;
/*     */ import kotlin.DeprecationLevel;
/*     */ import kotlin.jvm.JvmName;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000h\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\000\n\002\020\b\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\013\n\002\030\002\n\002\b\005\n\002\020\013\n\002\b\017\030\0002\0020\001By\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007\022\006\020\b\032\0020\t\022\b\020\n\032\004\030\0010\013\022\b\020\f\032\004\030\0010\r\022\b\020\016\032\004\030\0010\017\022\006\020\020\032\0020\021\022\b\020\022\032\004\030\0010\023\022\f\020\024\032\b\022\004\022\0020\0260\025\022\f\020\027\032\b\022\004\022\0020\0300\025\022\006\020\031\032\0020\032¢\006\002\020\033J\017\020\016\032\004\030\0010\017H\007¢\006\002\b(J\023\020\027\032\b\022\004\022\0020\0300\025H\007¢\006\002\b)J\r\020\006\032\0020\007H\007¢\006\002\b*J\023\020+\032\0020,2\b\020-\032\004\030\0010\001H\002J\025\020.\032\0020,2\006\020/\032\0020\000H\000¢\006\002\b0J\b\0201\032\0020\005H\026J\017\020\f\032\004\030\0010\rH\007¢\006\002\b2J\023\020\024\032\b\022\004\022\0020\0260\025H\007¢\006\002\b3J\017\020\022\032\004\030\0010\023H\007¢\006\002\b4J\r\020\020\032\0020\021H\007¢\006\002\b5J\r\020\031\032\0020\032H\007¢\006\002\b6J\r\020\b\032\0020\tH\007¢\006\002\b7J\017\020\n\032\004\030\0010\013H\007¢\006\002\b8J\b\0209\032\0020\003H\026J\r\020%\032\0020&H\007¢\006\002\b:R\025\020\016\032\004\030\0010\0178\007¢\006\b\n\000\032\004\b\016\020\034R\031\020\027\032\b\022\004\022\0020\0300\0258G¢\006\b\n\000\032\004\b\027\020\035R\023\020\006\032\0020\0078\007¢\006\b\n\000\032\004\b\006\020\036R\025\020\f\032\004\030\0010\r8\007¢\006\b\n\000\032\004\b\f\020\037R\031\020\024\032\b\022\004\022\0020\0260\0258G¢\006\b\n\000\032\004\b\024\020\035R\025\020\022\032\004\030\0010\0238\007¢\006\b\n\000\032\004\b\022\020 R\023\020\020\032\0020\0218\007¢\006\b\n\000\032\004\b\020\020!R\023\020\031\032\0020\0328\007¢\006\b\n\000\032\004\b\031\020\"R\023\020\b\032\0020\t8\007¢\006\b\n\000\032\004\b\b\020#R\025\020\n\032\004\030\0010\0138\007¢\006\b\n\000\032\004\b\n\020$R\023\020%\032\0020&8G¢\006\b\n\000\032\004\b%\020'¨\006;"}, d2 = {"Lokhttp3/Address;", "", "uriHost", "", "uriPort", "", "dns", "Lokhttp3/Dns;", "socketFactory", "Ljavax/net/SocketFactory;", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "hostnameVerifier", "Ljavax/net/ssl/HostnameVerifier;", "certificatePinner", "Lokhttp3/CertificatePinner;", "proxyAuthenticator", "Lokhttp3/Authenticator;", "proxy", "Ljava/net/Proxy;", "protocols", "", "Lokhttp3/Protocol;", "connectionSpecs", "Lokhttp3/ConnectionSpec;", "proxySelector", "Ljava/net/ProxySelector;", "(Ljava/lang/String;ILokhttp3/Dns;Ljavax/net/SocketFactory;Ljavax/net/ssl/SSLSocketFactory;Ljavax/net/ssl/HostnameVerifier;Lokhttp3/CertificatePinner;Lokhttp3/Authenticator;Ljava/net/Proxy;Ljava/util/List;Ljava/util/List;Ljava/net/ProxySelector;)V", "()Lokhttp3/CertificatePinner;", "()Ljava/util/List;", "()Lokhttp3/Dns;", "()Ljavax/net/ssl/HostnameVerifier;", "()Ljava/net/Proxy;", "()Lokhttp3/Authenticator;", "()Ljava/net/ProxySelector;", "()Ljavax/net/SocketFactory;", "()Ljavax/net/ssl/SSLSocketFactory;", "url", "Lokhttp3/HttpUrl;", "()Lokhttp3/HttpUrl;", "-deprecated_certificatePinner", "-deprecated_connectionSpecs", "-deprecated_dns", "equals", "", "other", "equalsNonHost", "that", "equalsNonHost$okhttp", "hashCode", "-deprecated_hostnameVerifier", "-deprecated_protocols", "-deprecated_proxy", "-deprecated_proxyAuthenticator", "-deprecated_proxySelector", "-deprecated_socketFactory", "-deprecated_sslSocketFactory", "toString", "-deprecated_url", "okhttp"})
/*     */ public final class Address {
/*     */   @NotNull
/*     */   private final HttpUrl url;
/*     */   @NotNull
/*     */   private final List<Protocol> protocols;
/*     */   @NotNull
/*     */   private final List<ConnectionSpec> connectionSpecs;
/*     */   @NotNull
/*     */   private final Dns dns;
/*     */   @NotNull
/*     */   private final SocketFactory socketFactory;
/*     */   @Nullable
/*     */   private final SSLSocketFactory sslSocketFactory;
/*     */   @Nullable
/*     */   private final HostnameVerifier hostnameVerifier;
/*     */   @Nullable
/*     */   private final CertificatePinner certificatePinner;
/*     */   @NotNull
/*     */   private final Authenticator proxyAuthenticator;
/*     */   @Nullable
/*     */   private final Proxy proxy;
/*     */   @NotNull
/*     */   private final ProxySelector proxySelector;
/*     */   
/*  34 */   public Address(@NotNull String uriHost, int uriPort, @NotNull Dns dns, @NotNull SocketFactory socketFactory, @Nullable SSLSocketFactory sslSocketFactory, @Nullable HostnameVerifier hostnameVerifier, @Nullable CertificatePinner certificatePinner, @NotNull Authenticator proxyAuthenticator, @Nullable Proxy proxy, @NotNull List protocols, @NotNull List connectionSpecs, @NotNull ProxySelector proxySelector) { this.dns = dns; this.socketFactory = socketFactory; this.sslSocketFactory = sslSocketFactory; this.hostnameVerifier = hostnameVerifier; this.certificatePinner = certificatePinner; this.proxyAuthenticator = proxyAuthenticator; this.proxy = proxy; this.proxySelector = proxySelector;
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
/*  78 */     this.url = (new HttpUrl.Builder()).scheme((this.sslSocketFactory != null) ? "https" : "http").host(uriHost).port(uriPort).build();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  84 */     this.protocols = Util.toImmutableList(protocols);
/*     */ 
/*     */     
/*  87 */     this.connectionSpecs = Util.toImmutableList(connectionSpecs); }
/*     */   @JvmName(name = "dns") @NotNull public final Dns dns() { return this.dns; }
/*     */   @JvmName(name = "socketFactory") @NotNull public final SocketFactory socketFactory() { return this.socketFactory; }
/*     */   @JvmName(name = "sslSocketFactory") @Nullable public final SSLSocketFactory sslSocketFactory() { return this.sslSocketFactory; }
/*     */   @JvmName(name = "hostnameVerifier") @Nullable public final HostnameVerifier hostnameVerifier() { return this.hostnameVerifier; }
/*     */   @JvmName(name = "certificatePinner") @Nullable public final CertificatePinner certificatePinner() { return this.certificatePinner; } @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "url"), level = DeprecationLevel.ERROR) @JvmName(name = "-deprecated_url")
/*     */   @NotNull
/*  94 */   public final HttpUrl -deprecated_url() { return this.url; }
/*     */   @JvmName(name = "proxyAuthenticator") @NotNull public final Authenticator proxyAuthenticator() { return this.proxyAuthenticator; }
/*     */   @JvmName(name = "proxy") @Nullable public final Proxy proxy() { return this.proxy; }
/*     */   @JvmName(name = "proxySelector") @NotNull public final ProxySelector proxySelector() { return this.proxySelector; }
/*     */   @JvmName(name = "url") @NotNull public final HttpUrl url() { return this.url; }
/*     */   @JvmName(name = "protocols") @NotNull public final List<Protocol> protocols() { return this.protocols; }
/*     */   @JvmName(name = "connectionSpecs") @NotNull public final List<ConnectionSpec> connectionSpecs() { return this.connectionSpecs; } @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "dns"), level = DeprecationLevel.ERROR) @JvmName(name = "-deprecated_dns") @NotNull
/* 101 */   public final Dns -deprecated_dns() { return this.dns; }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "socketFactory"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_socketFactory")
/*     */   @NotNull
/*     */   public final SocketFactory -deprecated_socketFactory() {
/* 108 */     return this.socketFactory;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "proxyAuthenticator"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_proxyAuthenticator")
/*     */   @NotNull
/*     */   public final Authenticator -deprecated_proxyAuthenticator() {
/* 115 */     return this.proxyAuthenticator;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "protocols"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_protocols")
/*     */   @NotNull
/*     */   public final List<Protocol> -deprecated_protocols() {
/* 122 */     return this.protocols;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "connectionSpecs"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_connectionSpecs")
/*     */   @NotNull
/*     */   public final List<ConnectionSpec> -deprecated_connectionSpecs() {
/* 129 */     return this.connectionSpecs;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "proxySelector"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_proxySelector")
/*     */   @NotNull
/*     */   public final ProxySelector -deprecated_proxySelector() {
/* 136 */     return this.proxySelector;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "proxy"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_proxy")
/*     */   @Nullable
/*     */   public final Proxy -deprecated_proxy() {
/* 143 */     return this.proxy;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "sslSocketFactory"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_sslSocketFactory")
/*     */   @Nullable
/*     */   public final SSLSocketFactory -deprecated_sslSocketFactory() {
/* 150 */     return this.sslSocketFactory;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "hostnameVerifier"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_hostnameVerifier")
/*     */   @Nullable
/*     */   public final HostnameVerifier -deprecated_hostnameVerifier() {
/* 157 */     return this.hostnameVerifier;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "certificatePinner"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_certificatePinner")
/*     */   @Nullable
/*     */   public final CertificatePinner -deprecated_certificatePinner() {
/* 164 */     return this.certificatePinner;
/*     */   }
/*     */   public boolean equals(@Nullable Object other) {
/* 167 */     return (other instanceof Address && Intrinsics.areEqual(this.url, ((Address)other).url) && 
/*     */       
/* 169 */       equalsNonHost$okhttp((Address)other));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 173 */     int result = 17;
/* 174 */     result = 31 * result + this.url.hashCode();
/* 175 */     result = 31 * result + this.dns.hashCode();
/* 176 */     result = 31 * result + this.proxyAuthenticator.hashCode();
/* 177 */     result = 31 * result + this.protocols.hashCode();
/* 178 */     result = 31 * result + this.connectionSpecs.hashCode();
/* 179 */     result = 31 * result + this.proxySelector.hashCode();
/* 180 */     result = 31 * result + Objects.hashCode(this.proxy);
/* 181 */     result = 31 * result + Objects.hashCode(this.sslSocketFactory);
/* 182 */     result = 31 * result + Objects.hashCode(this.hostnameVerifier);
/* 183 */     result = 31 * result + Objects.hashCode(this.certificatePinner);
/* 184 */     return result;
/*     */   }
/*     */   
/*     */   public final boolean equalsNonHost$okhttp(@NotNull Address that) {
/* 188 */     Intrinsics.checkNotNullParameter(that, "that"); return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 197 */       (Intrinsics.areEqual(this.dns, that.dns) && Intrinsics.areEqual(this.proxyAuthenticator, that.proxyAuthenticator) && Intrinsics.areEqual(this.protocols, that.protocols) && Intrinsics.areEqual(this.connectionSpecs, that.connectionSpecs) && Intrinsics.areEqual(this.proxySelector, that.proxySelector) && Intrinsics.areEqual(this.proxy, that.proxy) && Intrinsics.areEqual(this.sslSocketFactory, that.sslSocketFactory) && Intrinsics.areEqual(this.hostnameVerifier, that.hostnameVerifier) && Intrinsics.areEqual(this.certificatePinner, that.certificatePinner) && this.url.port() == that.url.port());
/*     */   }
/*     */   @NotNull
/*     */   public String toString() {
/* 201 */     return "Address{" + 
/* 202 */       this.url.host() + ':' + this.url.port() + ", " + (
/* 203 */       (this.proxy != null) ? ("proxy=" + this.proxy) : ("proxySelector=" + this.proxySelector)) + "}";
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/Address.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */