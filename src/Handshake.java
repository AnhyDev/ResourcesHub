/*     */ package okhttp3;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.security.Principal;
/*     */ import java.security.cert.Certificate;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import javax.net.ssl.SSLSession;
/*     */ import kotlin.Deprecated;
/*     */ import kotlin.DeprecationLevel;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.ReplaceWith;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.jvm.JvmName;
/*     */ import kotlin.jvm.JvmStatic;
/*     */ import kotlin.jvm.functions.Function0;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000H\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\007\n\002\020\016\n\002\b\004\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\b\030\000 &2\0020\001:\001&B9\b\000\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\f\020\006\032\b\022\004\022\0020\b0\007\022\022\020\t\032\016\022\n\022\b\022\004\022\0020\b0\0070\n¢\006\002\020\013J\r\020\004\032\0020\005H\007¢\006\002\b\032J\023\020\033\032\0020\0342\b\020\035\032\004\030\0010\001H\002J\b\020\036\032\0020\037H\026J\023\020\006\032\b\022\004\022\0020\b0\007H\007¢\006\002\b J\017\020\016\032\004\030\0010\017H\007¢\006\002\b!J\023\020\021\032\b\022\004\022\0020\b0\007H\007¢\006\002\b\"J\017\020\024\032\004\030\0010\017H\007¢\006\002\b#J\r\020\002\032\0020\003H\007¢\006\002\b$J\b\020%\032\0020\027H\026R\023\020\004\032\0020\0058\007¢\006\b\n\000\032\004\b\004\020\fR\031\020\006\032\b\022\004\022\0020\b0\0078\007¢\006\b\n\000\032\004\b\006\020\rR\023\020\016\032\004\030\0010\0178G¢\006\006\032\004\b\016\020\020R!\020\021\032\b\022\004\022\0020\b0\0078GX\002¢\006\f\n\004\b\022\020\023\032\004\b\021\020\rR\023\020\024\032\004\030\0010\0178G¢\006\006\032\004\b\024\020\020R\023\020\002\032\0020\0038\007¢\006\b\n\000\032\004\b\002\020\025R\030\020\026\032\0020\027*\0020\b8BX\004¢\006\006\032\004\b\030\020\031¨\006'"}, d2 = {"Lokhttp3/Handshake;", "", "tlsVersion", "Lokhttp3/TlsVersion;", "cipherSuite", "Lokhttp3/CipherSuite;", "localCertificates", "", "Ljava/security/cert/Certificate;", "peerCertificatesFn", "Lkotlin/Function0;", "(Lokhttp3/TlsVersion;Lokhttp3/CipherSuite;Ljava/util/List;Lkotlin/jvm/functions/Function0;)V", "()Lokhttp3/CipherSuite;", "()Ljava/util/List;", "localPrincipal", "Ljava/security/Principal;", "()Ljava/security/Principal;", "peerCertificates", "peerCertificates$delegate", "Lkotlin/Lazy;", "peerPrincipal", "()Lokhttp3/TlsVersion;", "name", "", "getName", "(Ljava/security/cert/Certificate;)Ljava/lang/String;", "-deprecated_cipherSuite", "equals", "", "other", "hashCode", "", "-deprecated_localCertificates", "-deprecated_localPrincipal", "-deprecated_peerCertificates", "-deprecated_peerPrincipal", "-deprecated_tlsVersion", "toString", "Companion", "okhttp"})
/*     */ public final class Handshake {
/*     */   @NotNull
/*     */   private final Lazy peerCertificates$delegate;
/*     */   @NotNull
/*     */   private final TlsVersion tlsVersion;
/*     */   @NotNull
/*     */   private final CipherSuite cipherSuite;
/*     */   @NotNull
/*     */   private final List<Certificate> localCertificates;
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*  34 */   public Handshake(@NotNull TlsVersion tlsVersion, @NotNull CipherSuite cipherSuite, @NotNull List<Certificate> localCertificates, @NotNull Function0 peerCertificatesFn) { this.tlsVersion = tlsVersion; this.cipherSuite = cipherSuite; this.localCertificates = localCertificates;
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
/*  51 */     this.peerCertificates$delegate = LazyKt.lazy(new Handshake$peerCertificates$2(peerCertificatesFn)); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 3, d1 = {"\000\f\n\000\n\002\020 \n\002\030\002\n\000\020\000\032\b\022\004\022\0020\0020\001H\n¢\006\002\b\003"}, d2 = {"<anonymous>", "", "Ljava/security/cert/Certificate;", "invoke"}) static final class Handshake$peerCertificates$2 extends Lambda implements Function0<List<? extends Certificate>> { @NotNull public final List<Certificate> invoke() { List<Certificate> list;
/*     */       try {
/*  53 */         list = (List)this.$peerCertificatesFn.invoke();
/*  54 */       } catch (SSLPeerUnverifiedException spue) {
/*  55 */         boolean bool = false; list = CollectionsKt.emptyList();
/*     */       } 
/*     */       return list; }
/*     */     Handshake$peerCertificates$2(Function0 param1Function0) { super(0); } }
/*     */    @JvmName(name = "tlsVersion")
/*     */   @NotNull
/*     */   public final TlsVersion tlsVersion() { return this.tlsVersion; } @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "tlsVersion"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_tlsVersion")
/*     */   @NotNull
/*  64 */   public final TlsVersion -deprecated_tlsVersion() { return this.tlsVersion; }
/*     */   @JvmName(name = "cipherSuite") @NotNull public final CipherSuite cipherSuite() { return this.cipherSuite; }
/*     */   @JvmName(name = "localCertificates")
/*     */   @NotNull
/*     */   public final List<Certificate> localCertificates() { return this.localCertificates; } @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "cipherSuite"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_cipherSuite")
/*     */   @NotNull
/*  71 */   public final CipherSuite -deprecated_cipherSuite() { return this.cipherSuite; }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "peerCertificates"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_peerCertificates")
/*     */   @NotNull
/*     */   public final List<Certificate> -deprecated_peerCertificates() {
/*  78 */     return peerCertificates();
/*     */   }
/*     */   @JvmName(name = "peerPrincipal")
/*     */   @Nullable
/*     */   public final Principal peerPrincipal() {
/*  83 */     if (!(CollectionsKt.firstOrNull(peerCertificates()) instanceof X509Certificate)) CollectionsKt.firstOrNull(peerCertificates());  (X509Certificate)null; return ((X509Certificate)null != null) ? ((X509Certificate)null).getSubjectX500Principal() : null;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "peerPrincipal"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_peerPrincipal")
/*     */   @Nullable
/*     */   public final Principal -deprecated_peerPrincipal() {
/*  90 */     return peerPrincipal();
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "localCertificates"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_localCertificates")
/*     */   @NotNull
/*     */   public final List<Certificate> -deprecated_localCertificates() {
/*  97 */     return this.localCertificates;
/*     */   }
/*     */   @JvmName(name = "localPrincipal")
/*     */   @Nullable
/*     */   public final Principal localPrincipal() {
/* 102 */     if (!(CollectionsKt.firstOrNull(this.localCertificates) instanceof X509Certificate)) CollectionsKt.firstOrNull(this.localCertificates);  (X509Certificate)null; return ((X509Certificate)null != null) ? ((X509Certificate)null).getSubjectX500Principal() : null;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "localPrincipal"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_localPrincipal")
/*     */   @Nullable
/*     */   public final Principal -deprecated_localPrincipal() {
/* 109 */     return localPrincipal();
/*     */   }
/*     */   public boolean equals(@Nullable Object other) {
/* 112 */     return (other instanceof Handshake && ((Handshake)other).tlsVersion == this.tlsVersion && Intrinsics.areEqual(((Handshake)other).cipherSuite, this.cipherSuite) && Intrinsics.areEqual(((Handshake)other).peerCertificates(), peerCertificates()) && Intrinsics.areEqual(((Handshake)other).localCertificates, this.localCertificates));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 120 */     int result = 17;
/* 121 */     result = 31 * result + this.tlsVersion.hashCode();
/* 122 */     result = 31 * result + this.cipherSuite.hashCode();
/* 123 */     result = 31 * result + peerCertificates().hashCode();
/* 124 */     result = 31 * result + this.localCertificates.hashCode();
/* 125 */     return result;
/*     */   }
/*     */   @NotNull
/*     */   public String toString() {
/* 129 */     Iterable<Certificate> $this$map$iv = peerCertificates(); int $i$f$map = 0;
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
/* 201 */     Iterable<Certificate> iterable1 = $this$map$iv; Collection<String> collection1 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)); int $i$f$mapTo = 0;
/* 202 */     for (Certificate item$iv$iv : iterable1) {
/* 203 */       Certificate certificate1 = item$iv$iv; Collection<String> collection = collection1; int $i$a$-map-Handshake$toString$peerCertificatesString$1 = 0; String str = getName(certificate1); collection.add(str);
/* 204 */     }  String peerCertificatesString = ((List)collection1).toString(); $this$map$iv = this.localCertificates; StringBuilder stringBuilder = (new StringBuilder()).append("Handshake{").append("tlsVersion=").append(this.tlsVersion).append(' ').append("cipherSuite=").append(this.cipherSuite).append(' ').append("peerCertificates=").append(peerCertificatesString).append(' ').append("localCertificates="); $i$f$map = 0;
/* 205 */     Iterable<Certificate> $this$mapTo$iv$iv = $this$map$iv; Collection<String> destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)); $i$f$mapTo = 0;
/* 206 */     for (Certificate item$iv$iv : $this$mapTo$iv$iv) {
/* 207 */       Certificate it = item$iv$iv; Collection<String> collection = destination$iv$iv; int $i$a$-map-Handshake$toString$1 = 0; String str = getName(it); collection.add(str);
/* 208 */     }  List list = (List)destination$iv$iv;
/*     */     return stringBuilder.append(list).append('}').toString();
/*     */   }
/*     */   
/*     */   private final String getName(Certificate $this$name) {
/*     */     Certificate certificate = $this$name;
/*     */     Intrinsics.checkNotNullExpressionValue($this$name.getType(), "type");
/*     */     return (certificate instanceof X509Certificate) ? ((X509Certificate)$this$name).getSubjectDN().toString() : $this$name.getType();
/*     */   }
/*     */   
/*     */   @JvmName(name = "peerCertificates")
/*     */   @NotNull
/*     */   public final List<Certificate> peerCertificates() {
/*     */     Lazy lazy = this.peerCertificates$delegate;
/*     */     Handshake handshake = this;
/*     */     Object object = null;
/*     */     boolean bool = false;
/*     */     return (List<Certificate>)lazy.getValue();
/*     */   }
/*     */   
/*     */   @JvmStatic
/*     */   @JvmName(name = "get")
/*     */   @NotNull
/*     */   public static final Handshake get(@NotNull SSLSession $this$handshake) throws IOException {
/*     */     return Companion.get($this$handshake);
/*     */   }
/*     */   
/*     */   @JvmStatic
/*     */   @NotNull
/*     */   public static final Handshake get(@NotNull TlsVersion tlsVersion, @NotNull CipherSuite cipherSuite, @NotNull List<? extends Certificate> peerCertificates, @NotNull List<? extends Certificate> localCertificates) {
/*     */     return Companion.get(tlsVersion, cipherSuite, peerCertificates, localCertificates);
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000:\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\002\b\003\n\002\020\021\n\002\b\002\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\025\020\003\032\0020\0042\006\020\005\032\0020\006H\007¢\006\002\b\007J4\020\003\032\0020\0042\006\020\b\032\0020\t2\006\020\n\032\0020\0132\f\020\f\032\b\022\004\022\0020\0160\r2\f\020\017\032\b\022\004\022\0020\0160\rH\007J\021\020\020\032\0020\004*\0020\006H\007¢\006\002\b\003J!\020\021\032\b\022\004\022\0020\0160\r*\f\022\006\b\001\022\0020\016\030\0010\022H\002¢\006\002\020\023¨\006\024"}, d2 = {"Lokhttp3/Handshake$Companion;", "", "()V", "get", "Lokhttp3/Handshake;", "sslSession", "Ljavax/net/ssl/SSLSession;", "-deprecated_get", "tlsVersion", "Lokhttp3/TlsVersion;", "cipherSuite", "Lokhttp3/CipherSuite;", "peerCertificates", "", "Ljava/security/cert/Certificate;", "localCertificates", "handshake", "toImmutableList", "", "([Ljava/security/cert/Certificate;)Ljava/util/List;", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */     
/*     */     @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 3, d1 = {"\000\f\n\000\n\002\020 \n\002\030\002\n\000\020\000\032\b\022\004\022\0020\0020\001H\n¢\006\002\b\003"}, d2 = {"<anonymous>", "", "Ljava/security/cert/Certificate;", "invoke"})
/*     */     static final class Handshake$Companion$handshake$1 extends Lambda implements Function0<List<? extends Certificate>> {
/*     */       @NotNull
/*     */       public final List<Certificate> invoke() {
/*     */         return this.$peerCertificatesCopy;
/*     */       }
/*     */       
/*     */       Handshake$Companion$handshake$1(List param2List) {
/*     */         super(0);
/*     */       }
/*     */     }
/*     */     
/*     */     @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 3, d1 = {"\000\f\n\000\n\002\020 \n\002\030\002\n\000\020\000\032\b\022\004\022\0020\0020\001H\n¢\006\002\b\003"}, d2 = {"<anonymous>", "", "Ljava/security/cert/Certificate;", "invoke"})
/*     */     static final class Handshake$Companion$get$1 extends Lambda implements Function0<List<? extends Certificate>> {
/*     */       @NotNull
/*     */       public final List<Certificate> invoke() {
/*     */         return this.$peerCertificatesCopy;
/*     */       }
/*     */       
/*     */       Handshake$Companion$get$1(List param2List) {
/*     */         super(0);
/*     */       }
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @JvmName(name = "get")
/*     */     @NotNull
/*     */     public final Handshake get(@NotNull SSLSession $this$handshake) throws IOException {
/*     */       List list1;
/*     */       Intrinsics.checkNotNullParameter($this$handshake, "$this$handshake");
/*     */       String str1 = $this$handshake.getCipherSuite();
/*     */       boolean bool1 = false, bool2 = false;
/*     */       if (str1 == null) {
/*     */         int $i$a$-checkNotNull-Handshake$Companion$handshake$cipherSuiteString$1 = 0;
/*     */         String str = "cipherSuite == null";
/*     */         throw (Throwable)new IllegalStateException(str.toString());
/*     */       } 
/*     */       String cipherSuiteString = str1;
/*     */       String str2 = cipherSuiteString;
/*     */       switch (str2.hashCode()) {
/*     */         case 1208658923:
/*     */           if (str2.equals("SSL_NULL_WITH_NULL_NULL"))
/*     */             throw (Throwable)new IOException("cipherSuite == " + cipherSuiteString); 
/*     */           break;
/*     */         case 1019404634:
/*     */           if (str2.equals("TLS_NULL_WITH_NULL_NULL"))
/*     */             throw (Throwable)new IOException("cipherSuite == " + cipherSuiteString); 
/*     */           break;
/*     */       } 
/*     */       CipherSuite cipherSuite = CipherSuite.Companion.forJavaName(cipherSuiteString);
/*     */       String str3 = $this$handshake.getProtocol();
/*     */       boolean bool3 = false, bool4 = false;
/*     */       if (str3 == null) {
/*     */         int $i$a$-checkNotNull-Handshake$Companion$handshake$tlsVersionString$1 = 0;
/*     */         String str = "tlsVersion == null";
/*     */         throw (Throwable)new IllegalStateException(str.toString());
/*     */       } 
/*     */       String tlsVersionString = str3;
/*     */       if (Intrinsics.areEqual("NONE", tlsVersionString))
/*     */         throw (Throwable)new IOException("tlsVersion == NONE"); 
/*     */       TlsVersion tlsVersion = TlsVersion.Companion.forJavaName(tlsVersionString);
/*     */       try {
/*     */         list1 = toImmutableList($this$handshake.getPeerCertificates());
/*     */       } catch (SSLPeerUnverifiedException _) {
/*     */         boolean bool = false;
/*     */         list1 = CollectionsKt.emptyList();
/*     */       } 
/*     */       List peerCertificatesCopy = list1;
/*     */       return new Handshake(tlsVersion, cipherSuite, toImmutableList($this$handshake.getLocalCertificates()), new Handshake$Companion$handshake$1(peerCertificatesCopy));
/*     */     }
/*     */     
/*     */     private final List<Certificate> toImmutableList(Certificate[] $this$toImmutableList) {
/*     */       return ($this$toImmutableList != null) ? Util.immutableListOf((Object[])Arrays.<Certificate>copyOf($this$toImmutableList, $this$toImmutableList.length)) : CollectionsKt.emptyList();
/*     */     }
/*     */     
/*     */     @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {}, expression = "sslSession.handshake()"), level = DeprecationLevel.ERROR)
/*     */     @JvmName(name = "-deprecated_get")
/*     */     @NotNull
/*     */     public final Handshake -deprecated_get(@NotNull SSLSession sslSession) throws IOException {
/*     */       Intrinsics.checkNotNullParameter(sslSession, "sslSession");
/*     */       return get(sslSession);
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @NotNull
/*     */     public final Handshake get(@NotNull TlsVersion tlsVersion, @NotNull CipherSuite cipherSuite, @NotNull List peerCertificates, @NotNull List localCertificates) {
/*     */       Intrinsics.checkNotNullParameter(tlsVersion, "tlsVersion");
/*     */       Intrinsics.checkNotNullParameter(cipherSuite, "cipherSuite");
/*     */       Intrinsics.checkNotNullParameter(peerCertificates, "peerCertificates");
/*     */       Intrinsics.checkNotNullParameter(localCertificates, "localCertificates");
/*     */       List peerCertificatesCopy = Util.toImmutableList(peerCertificates);
/*     */       return new Handshake(tlsVersion, cipherSuite, Util.toImmutableList(localCertificates), new Handshake$Companion$get$1(peerCertificatesCopy));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/Handshake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */