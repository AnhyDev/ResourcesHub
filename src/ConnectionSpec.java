/*     */ package okhttp3;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import javax.net.ssl.SSLSocket;
/*     */ import kotlin.Deprecated;
/*     */ import kotlin.DeprecationLevel;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.ReplaceWith;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.comparisons.ComparisonsKt;
/*     */ import kotlin.jvm.JvmField;
/*     */ import kotlin.jvm.JvmName;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.internal.Util;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000F\n\002\030\002\n\002\020\000\n\000\n\002\020\013\n\002\b\002\n\002\020\021\n\002\020\016\n\002\b\003\n\002\020 \n\002\030\002\n\002\b\004\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\002\b\006\n\002\020\b\n\002\b\t\030\000 $2\0020\001:\002#$B7\b\000\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003\022\016\020\005\032\n\022\004\022\0020\007\030\0010\006\022\016\020\b\032\n\022\004\022\0020\007\030\0010\006¢\006\002\020\tJ\035\020\022\032\0020\0232\006\020\024\032\0020\0252\006\020\026\032\0020\003H\000¢\006\002\b\027J\025\020\n\032\n\022\004\022\0020\f\030\0010\013H\007¢\006\002\b\030J\023\020\031\032\0020\0032\b\020\032\032\004\030\0010\001H\002J\b\020\033\032\0020\034H\026J\016\020\035\032\0020\0032\006\020\036\032\0020\025J\030\020\037\032\0020\0002\006\020\024\032\0020\0252\006\020\026\032\0020\003H\002J\r\020\004\032\0020\003H\007¢\006\002\b J\025\020\020\032\n\022\004\022\0020\021\030\0010\013H\007¢\006\002\b!J\b\020\"\032\0020\007H\026R\031\020\n\032\n\022\004\022\0020\f\030\0010\0138G¢\006\006\032\004\b\n\020\rR\030\020\005\032\n\022\004\022\0020\007\030\0010\006X\004¢\006\004\n\002\020\016R\023\020\002\032\0020\0038\007¢\006\b\n\000\032\004\b\002\020\017R\023\020\004\032\0020\0038\007¢\006\b\n\000\032\004\b\004\020\017R\031\020\020\032\n\022\004\022\0020\021\030\0010\0138G¢\006\006\032\004\b\020\020\rR\030\020\b\032\n\022\004\022\0020\007\030\0010\006X\004¢\006\004\n\002\020\016¨\006%"}, d2 = {"Lokhttp3/ConnectionSpec;", "", "isTls", "", "supportsTlsExtensions", "cipherSuitesAsString", "", "", "tlsVersionsAsString", "(ZZ[Ljava/lang/String;[Ljava/lang/String;)V", "cipherSuites", "", "Lokhttp3/CipherSuite;", "()Ljava/util/List;", "[Ljava/lang/String;", "()Z", "tlsVersions", "Lokhttp3/TlsVersion;", "apply", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "isFallback", "apply$okhttp", "-deprecated_cipherSuites", "equals", "other", "hashCode", "", "isCompatible", "socket", "supportedSpec", "-deprecated_supportsTlsExtensions", "-deprecated_tlsVersions", "toString", "Builder", "Companion", "okhttp"})
/*     */ public final class ConnectionSpec
/*     */ {
/*     */   private final boolean isTls;
/*     */   private final boolean supportsTlsExtensions;
/*     */   private final String[] cipherSuitesAsString;
/*     */   private final String[] tlsVersionsAsString;
/*     */   private static final CipherSuite[] RESTRICTED_CIPHER_SUITES;
/*     */   private static final CipherSuite[] APPROVED_CIPHER_SUITES;
/*     */   @JvmField
/*     */   @NotNull
/*     */   public static final ConnectionSpec RESTRICTED_TLS;
/*     */   @JvmField
/*     */   @NotNull
/*     */   public static final ConnectionSpec MODERN_TLS;
/*     */   @JvmField
/*     */   @NotNull
/*     */   public static final ConnectionSpec COMPATIBLE_TLS;
/*     */   @JvmField
/*     */   @NotNull
/*     */   public static final ConnectionSpec CLEARTEXT;
/*     */   
/*     */   public ConnectionSpec(boolean isTls, boolean supportsTlsExtensions, @Nullable String[] cipherSuitesAsString, @Nullable String[] tlsVersionsAsString) {
/*  46 */     this.isTls = isTls; this.supportsTlsExtensions = supportsTlsExtensions; this.cipherSuitesAsString = cipherSuitesAsString; this.tlsVersionsAsString = tlsVersionsAsString; } @JvmName(name = "isTls")
/*  47 */   public final boolean isTls() { return this.isTls; } @JvmName(name = "supportsTlsExtensions")
/*  48 */   public final boolean supportsTlsExtensions() { return this.supportsTlsExtensions; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JvmName(name = "cipherSuites")
/*     */   @Nullable
/*     */   public final List<CipherSuite> cipherSuites() {
/*  59 */     String[] arrayOfString1 = this.cipherSuitesAsString; int $i$f$map = 0;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 351 */     String[] arrayOfString2 = arrayOfString1; Collection<CipherSuite> destination$iv$iv = new ArrayList(arrayOfString1.length); int $i$f$mapTo = 0;
/* 352 */     for (String item$iv$iv : arrayOfString2) {
/* 353 */       Object object = item$iv$iv; Collection<CipherSuite> collection = destination$iv$iv; int $i$a$-map-ConnectionSpec$cipherSuites$1 = 0; CipherSuite cipherSuite = CipherSuite.Companion.forJavaName((String)object); collection.add(cipherSuite);
/* 354 */     }  return (this.cipherSuitesAsString != null) ? CollectionsKt.toList(destination$iv$iv) : null;
/* 355 */   } @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "cipherSuites"), level = DeprecationLevel.ERROR) @JvmName(name = "-deprecated_cipherSuites") @Nullable public final List<CipherSuite> -deprecated_cipherSuites() { return cipherSuites(); } @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "tlsVersions"), level = DeprecationLevel.ERROR) @JvmName(name = "-deprecated_tlsVersions") @Nullable public final List<TlsVersion> -deprecated_tlsVersions() { return tlsVersions(); } @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "supportsTlsExtensions"), level = DeprecationLevel.ERROR) @JvmName(name = "-deprecated_supportsTlsExtensions") public final boolean -deprecated_supportsTlsExtensions() { return this.supportsTlsExtensions; } public final void apply$okhttp(@NotNull SSLSocket sslSocket, boolean isFallback) { Intrinsics.checkNotNullParameter(sslSocket, "sslSocket"); ConnectionSpec specToApply = supportedSpec(sslSocket, isFallback); if (specToApply.tlsVersions() != null) sslSocket.setEnabledProtocols(specToApply.tlsVersionsAsString);  if (specToApply.cipherSuites() != null) sslSocket.setEnabledCipherSuites(specToApply.cipherSuitesAsString);  } private final ConnectionSpec supportedSpec(SSLSocket sslSocket, boolean isFallback) { Intrinsics.checkNotNullExpressionValue(sslSocket.getEnabledCipherSuites(), "sslSocket.enabledCipherSuites"); String[] cipherSuitesIntersection = (this.cipherSuitesAsString != null) ? Util.intersect(sslSocket.getEnabledCipherSuites(), this.cipherSuitesAsString, CipherSuite.Companion.getORDER_BY_NAME$okhttp()) : sslSocket.getEnabledCipherSuites(); Intrinsics.checkNotNullExpressionValue(sslSocket.getEnabledProtocols(), "sslSocket.enabledProtocols"); String[] tlsVersionsIntersection = (this.tlsVersionsAsString != null) ? Util.intersect(sslSocket.getEnabledProtocols(), this.tlsVersionsAsString, ComparisonsKt.naturalOrder()) : sslSocket.getEnabledProtocols(); String[] supportedCipherSuites = sslSocket.getSupportedCipherSuites(); Intrinsics.checkNotNullExpressionValue(supportedCipherSuites, "supportedCipherSuites"); int indexOfFallbackScsv = Util.indexOf(supportedCipherSuites, "TLS_FALLBACK_SCSV", CipherSuite.Companion.getORDER_BY_NAME$okhttp()); if (isFallback && indexOfFallbackScsv != -1) { Intrinsics.checkNotNullExpressionValue(cipherSuitesIntersection, "cipherSuitesIntersection"); Intrinsics.checkNotNullExpressionValue(supportedCipherSuites[indexOfFallbackScsv], "supportedCipherSuites[indexOfFallbackScsv]"); cipherSuitesIntersection = Util.concat(cipherSuitesIntersection, supportedCipherSuites[indexOfFallbackScsv]); }  Intrinsics.checkNotNullExpressionValue(cipherSuitesIntersection, "cipherSuitesIntersection"); Intrinsics.checkNotNullExpressionValue(tlsVersionsIntersection, "tlsVersionsIntersection"); return (new Builder(this)).cipherSuites(Arrays.<String>copyOf(cipherSuitesIntersection, cipherSuitesIntersection.length)).tlsVersions(Arrays.<String>copyOf(tlsVersionsIntersection, tlsVersionsIntersection.length)).build(); } @JvmName(name = "tlsVersions") @Nullable public final List<TlsVersion> tlsVersions() { String[] arrayOfString1 = this.tlsVersionsAsString; int $i$f$map = 0; String[] arrayOfString2 = arrayOfString1; Collection<TlsVersion> destination$iv$iv = new ArrayList(arrayOfString1.length); int $i$f$mapTo = 0;
/* 356 */     for (String item$iv$iv : arrayOfString2) {
/* 357 */       Object object = item$iv$iv; Collection<TlsVersion> collection = destination$iv$iv; int $i$a$-map-ConnectionSpec$tlsVersions$1 = 0; TlsVersion tlsVersion = TlsVersion.Companion.forJavaName((String)object); collection.add(tlsVersion);
/* 358 */     }  return (this.tlsVersionsAsString != null) ? CollectionsKt.toList(destination$iv$iv) : null; } public final boolean isCompatible(@NotNull SSLSocket socket) { Intrinsics.checkNotNullParameter(socket, "socket"); if (!this.isTls) return false;  if (this.tlsVersionsAsString != null && !Util.hasIntersection(this.tlsVersionsAsString, socket.getEnabledProtocols(), ComparisonsKt.naturalOrder())) return false;  if (this.cipherSuitesAsString != null && !Util.hasIntersection(this.cipherSuitesAsString, socket.getEnabledCipherSuites(), CipherSuite.Companion.getORDER_BY_NAME$okhttp())) return false;  return true; } public boolean equals(@Nullable Object other) { if (!(other instanceof ConnectionSpec)) return false;  if (other == this) return true;  if (this.isTls != ((ConnectionSpec)other).isTls) return false;  if (this.isTls) { if (!Arrays.equals((Object[])this.cipherSuitesAsString, (Object[])((ConnectionSpec)other).cipherSuitesAsString)) return false;  if (!Arrays.equals((Object[])this.tlsVersionsAsString, (Object[])((ConnectionSpec)other).tlsVersionsAsString)) return false;  if (this.supportsTlsExtensions != ((ConnectionSpec)other).supportsTlsExtensions) return false;  }  return true; } public int hashCode() { int result = 17; if (this.isTls) { String[] arrayOfString = this.cipherSuitesAsString; boolean bool = false; result = 31 * result + ((this.cipherSuitesAsString != null) ? Arrays.hashCode((Object[])arrayOfString) : 0); arrayOfString = this.tlsVersionsAsString; bool = false; result = 31 * result + ((this.tlsVersionsAsString != null) ? Arrays.hashCode((Object[])arrayOfString) : 0); result = 31 * result + (this.supportsTlsExtensions ? 0 : 1); }  return result; } @NotNull public String toString() { if (!this.isTls) return "ConnectionSpec()";  return "ConnectionSpec(" + "cipherSuites=" + Objects.toString(cipherSuites(), "[all enabled]") + ", " + "tlsVersions=" + Objects.toString(tlsVersions(), "[all enabled]") + ", " + "supportsTlsExtensions=" + this.supportsTlsExtensions + ')'; } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0004\n\002\030\002\n\002\020\000\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\021\n\002\020\016\n\002\b\022\n\002\030\002\n\000\n\002\030\002\n\002\b\002\030\0002\0020\001B\017\b\020\022\006\020\002\032\0020\003¢\006\002\020\004B\017\b\026\022\006\020\005\032\0020\006¢\006\002\020\007J\006\020\031\032\0020\000J\006\020\032\032\0020\000J\006\020\033\032\0020\006J\037\020\b\032\0020\0002\022\020\b\032\n\022\006\b\001\022\0020\n0\t\"\0020\n¢\006\002\020\034J\037\020\b\032\0020\0002\022\020\b\032\n\022\006\b\001\022\0020\0350\t\"\0020\035¢\006\002\020\036J\020\020\020\032\0020\0002\006\020\020\032\0020\003H\007J\037\020\026\032\0020\0002\022\020\026\032\n\022\006\b\001\022\0020\n0\t\"\0020\n¢\006\002\020\034J\037\020\026\032\0020\0002\022\020\026\032\n\022\006\b\001\022\0020\0370\t\"\0020\037¢\006\002\020 R$\020\b\032\n\022\004\022\0020\n\030\0010\tX\016¢\006\020\n\002\020\017\032\004\b\013\020\f\"\004\b\r\020\016R\032\020\020\032\0020\003X\016¢\006\016\n\000\032\004\b\021\020\022\"\004\b\023\020\004R\032\020\002\032\0020\003X\016¢\006\016\n\000\032\004\b\024\020\022\"\004\b\025\020\004R$\020\026\032\n\022\004\022\0020\n\030\0010\tX\016¢\006\020\n\002\020\017\032\004\b\027\020\f\"\004\b\030\020\016¨\006!"}, d2 = {"Lokhttp3/ConnectionSpec$Builder;", "", "tls", "", "(Z)V", "connectionSpec", "Lokhttp3/ConnectionSpec;", "(Lokhttp3/ConnectionSpec;)V", "cipherSuites", "", "", "getCipherSuites$okhttp", "()[Ljava/lang/String;", "setCipherSuites$okhttp", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "supportsTlsExtensions", "getSupportsTlsExtensions$okhttp", "()Z", "setSupportsTlsExtensions$okhttp", "getTls$okhttp", "setTls$okhttp", "tlsVersions", "getTlsVersions$okhttp", "setTlsVersions$okhttp", "allEnabledCipherSuites", "allEnabledTlsVersions", "build", "([Ljava/lang/String;)Lokhttp3/ConnectionSpec$Builder;", "Lokhttp3/CipherSuite;", "([Lokhttp3/CipherSuite;)Lokhttp3/ConnectionSpec$Builder;", "Lokhttp3/TlsVersion;", "([Lokhttp3/TlsVersion;)Lokhttp3/ConnectionSpec$Builder;", "okhttp"}) public static final class Builder { @NotNull public final Builder tlsVersions(@NotNull TlsVersion... tlsVersions) { Intrinsics.checkNotNullParameter(tlsVersions, "tlsVersions"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-ConnectionSpec$Builder$tlsVersions$1 = 0; boolean bool = $this$apply.tls; boolean bool3 = false, bool4 = false; if (!bool) { int $i$a$-require-ConnectionSpec$Builder$tlsVersions$1$1 = 0; String str = "no TLS versions for cleartext connections"; throw (Throwable)new IllegalArgumentException(str.toString()); }  TlsVersion[] arrayOfTlsVersion1 = tlsVersions; int $i$f$map = 0; TlsVersion[] arrayOfTlsVersion2 = arrayOfTlsVersion1; Collection<String> destination$iv$iv = new ArrayList(arrayOfTlsVersion1.length); int $i$f$mapTo = 0;
/* 359 */       for (TlsVersion item$iv$iv : arrayOfTlsVersion2) {
/* 360 */         Object object = item$iv$iv; Collection<String> collection = destination$iv$iv; int $i$a$-map-ConnectionSpec$Builder$tlsVersions$1$strings$1 = 0; String str = object.javaName(); collection.add(str);
/* 361 */       }  Collection $this$toTypedArray$iv = destination$iv$iv; int $i$f$toTypedArray = 0;
/* 362 */       Collection thisCollection$iv = $this$toTypedArray$iv;
/* 363 */       if (thisCollection$iv.toArray((Object[])new String[0]) == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");  String[] strings = (String[])thisCollection$iv.toArray((Object[])new String[0]);
/*     */       return $this$apply.tlsVersions(Arrays.<String>copyOf(strings, strings.length)); }
/*     */ 
/*     */     
/*     */     private boolean tls;
/*     */     @Nullable
/*     */     private String[] cipherSuites;
/*     */     @Nullable
/*     */     private String[] tlsVersions;
/*     */     private boolean supportsTlsExtensions;
/*     */     
/*     */     public final boolean getTls$okhttp() {
/*     */       return this.tls;
/*     */     }
/*     */     
/*     */     public final void setTls$okhttp(boolean <set-?>) {
/*     */       this.tls = <set-?>;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public final String[] getCipherSuites$okhttp() {
/*     */       return this.cipherSuites;
/*     */     }
/*     */     
/*     */     public final void setCipherSuites$okhttp(@Nullable String[] <set-?>) {
/*     */       this.cipherSuites = <set-?>;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public final String[] getTlsVersions$okhttp() {
/*     */       return this.tlsVersions;
/*     */     }
/*     */     
/*     */     public final void setTlsVersions$okhttp(@Nullable String[] <set-?>) {
/*     */       this.tlsVersions = <set-?>;
/*     */     }
/*     */     
/*     */     public final boolean getSupportsTlsExtensions$okhttp() {
/*     */       return this.supportsTlsExtensions;
/*     */     }
/*     */     
/*     */     public final void setSupportsTlsExtensions$okhttp(boolean <set-?>) {
/*     */       this.supportsTlsExtensions = <set-?>;
/*     */     }
/*     */     
/*     */     public Builder(boolean tls) {
/*     */       this.tls = tls;
/*     */     }
/*     */     
/*     */     public Builder(@NotNull ConnectionSpec connectionSpec) {
/*     */       this.tls = connectionSpec.isTls();
/*     */       this.cipherSuites = connectionSpec.cipherSuitesAsString;
/*     */       this.tlsVersions = connectionSpec.tlsVersionsAsString;
/*     */       this.supportsTlsExtensions = connectionSpec.supportsTlsExtensions();
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder allEnabledCipherSuites() {
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-ConnectionSpec$Builder$allEnabledCipherSuites$1 = 0;
/*     */       boolean bool = $this$apply.tls;
/*     */       boolean bool3 = false, bool4 = false;
/*     */       if (!bool) {
/*     */         int $i$a$-require-ConnectionSpec$Builder$allEnabledCipherSuites$1$1 = 0;
/*     */         String str = "no cipher suites for cleartext connections";
/*     */         throw (Throwable)new IllegalArgumentException(str.toString());
/*     */       } 
/*     */       $this$apply.cipherSuites = (String[])null;
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder cipherSuites(@NotNull CipherSuite... cipherSuites) {
/*     */       Intrinsics.checkNotNullParameter(cipherSuites, "cipherSuites");
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-ConnectionSpec$Builder$cipherSuites$1 = 0;
/*     */       boolean bool = $this$apply.tls;
/*     */       boolean bool3 = false, bool4 = false;
/*     */       if (!bool) {
/*     */         int $i$a$-require-ConnectionSpec$Builder$cipherSuites$1$1 = 0;
/*     */         String str = "no cipher suites for cleartext connections";
/*     */         throw (Throwable)new IllegalArgumentException(str.toString());
/*     */       } 
/*     */       CipherSuite[] arrayOfCipherSuite1 = cipherSuites;
/*     */       int $i$f$map = 0;
/*     */       CipherSuite[] arrayOfCipherSuite2 = arrayOfCipherSuite1;
/*     */       Collection<String> destination$iv$iv = new ArrayList(arrayOfCipherSuite1.length);
/*     */       int $i$f$mapTo = 0;
/*     */       for (CipherSuite item$iv$iv : arrayOfCipherSuite2) {
/*     */         Object object = item$iv$iv;
/*     */         Collection<String> collection = destination$iv$iv;
/*     */         int $i$a$-map-ConnectionSpec$Builder$cipherSuites$1$strings$1 = 0;
/*     */         String str = object.javaName();
/*     */         collection.add(str);
/*     */       } 
/*     */       Collection $this$toTypedArray$iv = destination$iv$iv;
/*     */       int $i$f$toTypedArray = 0;
/*     */       Collection thisCollection$iv = $this$toTypedArray$iv;
/*     */       if (thisCollection$iv.toArray((Object[])new String[0]) == null)
/*     */         throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>"); 
/*     */       String[] strings = (String[])thisCollection$iv.toArray((Object[])new String[0]);
/*     */       return $this$apply.cipherSuites(Arrays.<String>copyOf(strings, strings.length));
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder cipherSuites(@NotNull String... cipherSuites) {
/*     */       Intrinsics.checkNotNullParameter(cipherSuites, "cipherSuites");
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-ConnectionSpec$Builder$cipherSuites$2 = 0;
/*     */       boolean bool = $this$apply.tls;
/*     */       boolean bool4 = false, bool6 = false;
/*     */       if (!bool) {
/*     */         int $i$a$-require-ConnectionSpec$Builder$cipherSuites$2$1 = 0;
/*     */         String str = "no cipher suites for cleartext connections";
/*     */         throw (Throwable)new IllegalArgumentException(str.toString());
/*     */       } 
/*     */       String[] arrayOfString1 = cipherSuites;
/*     */       bool4 = false;
/*     */       String[] arrayOfString2 = arrayOfString1;
/*     */       boolean bool7 = false, bool3 = !((arrayOfString2.length == 0) ? 1 : 0) ? true : false;
/*     */       bool4 = false;
/*     */       boolean bool5 = false;
/*     */       if (!bool3) {
/*     */         int $i$a$-require-ConnectionSpec$Builder$cipherSuites$2$2 = 0;
/*     */         String str = "At least one cipher suite is required";
/*     */         throw (Throwable)new IllegalArgumentException(str.toString());
/*     */       } 
/*     */       if (cipherSuites.clone() == null)
/*     */         throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.String>"); 
/*     */       $this$apply.cipherSuites = (String[])cipherSuites.clone();
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder allEnabledTlsVersions() {
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-ConnectionSpec$Builder$allEnabledTlsVersions$1 = 0;
/*     */       boolean bool = $this$apply.tls;
/*     */       boolean bool3 = false, bool4 = false;
/*     */       if (!bool) {
/*     */         int $i$a$-require-ConnectionSpec$Builder$allEnabledTlsVersions$1$1 = 0;
/*     */         String str = "no TLS versions for cleartext connections";
/*     */         throw (Throwable)new IllegalArgumentException(str.toString());
/*     */       } 
/*     */       $this$apply.tlsVersions = (String[])null;
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder tlsVersions(@NotNull String... tlsVersions) {
/*     */       Intrinsics.checkNotNullParameter(tlsVersions, "tlsVersions");
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-ConnectionSpec$Builder$tlsVersions$2 = 0;
/*     */       boolean bool = $this$apply.tls;
/*     */       boolean bool4 = false, bool6 = false;
/*     */       if (!bool) {
/*     */         int $i$a$-require-ConnectionSpec$Builder$tlsVersions$2$1 = 0;
/*     */         String str = "no TLS versions for cleartext connections";
/*     */         throw (Throwable)new IllegalArgumentException(str.toString());
/*     */       } 
/*     */       String[] arrayOfString1 = tlsVersions;
/*     */       bool4 = false;
/*     */       String[] arrayOfString2 = arrayOfString1;
/*     */       boolean bool7 = false, bool3 = !((arrayOfString2.length == 0) ? 1 : 0) ? true : false;
/*     */       bool4 = false;
/*     */       boolean bool5 = false;
/*     */       if (!bool3) {
/*     */         int $i$a$-require-ConnectionSpec$Builder$tlsVersions$2$2 = 0;
/*     */         String str = "At least one TLS version is required";
/*     */         throw (Throwable)new IllegalArgumentException(str.toString());
/*     */       } 
/*     */       if (tlsVersions.clone() == null)
/*     */         throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.String>"); 
/*     */       $this$apply.tlsVersions = (String[])tlsVersions.clone();
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @Deprecated(message = "since OkHttp 3.13 all TLS-connections are expected to support TLS extensions.\nIn a future release setting this to true will be unnecessary and setting it to false\nwill have no effect.")
/*     */     @NotNull
/*     */     public final Builder supportsTlsExtensions(boolean supportsTlsExtensions) {
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-ConnectionSpec$Builder$supportsTlsExtensions$1 = 0;
/*     */       boolean bool = $this$apply.tls;
/*     */       boolean bool3 = false, bool4 = false;
/*     */       if (!bool) {
/*     */         int $i$a$-require-ConnectionSpec$Builder$supportsTlsExtensions$1$1 = 0;
/*     */         String str = "no TLS extensions for cleartext connections";
/*     */         throw (Throwable)new IllegalArgumentException(str.toString());
/*     */       } 
/*     */       $this$apply.supportsTlsExtensions = supportsTlsExtensions;
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final ConnectionSpec build() {
/*     */       return new ConnectionSpec(this.tls, this.supportsTlsExtensions, this.cipherSuites, this.tlsVersions);
/*     */     } }
/*     */ 
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000 \n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\021\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\b\022\004\022\0020\0050\004X\004¢\006\004\n\002\020\006R\020\020\007\032\0020\b8\006X\004¢\006\002\n\000R\020\020\t\032\0020\b8\006X\004¢\006\002\n\000R\020\020\n\032\0020\b8\006X\004¢\006\002\n\000R\026\020\013\032\b\022\004\022\0020\0050\004X\004¢\006\004\n\002\020\006R\020\020\f\032\0020\b8\006X\004¢\006\002\n\000¨\006\r"}, d2 = {"Lokhttp3/ConnectionSpec$Companion;", "", "()V", "APPROVED_CIPHER_SUITES", "", "Lokhttp3/CipherSuite;", "[Lokhttp3/CipherSuite;", "CLEARTEXT", "Lokhttp3/ConnectionSpec;", "COMPATIBLE_TLS", "MODERN_TLS", "RESTRICTED_CIPHER_SUITES", "RESTRICTED_TLS", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */   }
/*     */   
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   static {
/*     */     RESTRICTED_CIPHER_SUITES = new CipherSuite[] { CipherSuite.TLS_AES_128_GCM_SHA256, CipherSuite.TLS_AES_256_GCM_SHA384, CipherSuite.TLS_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 };
/*     */     APPROVED_CIPHER_SUITES = new CipherSuite[] { 
/*     */         CipherSuite.TLS_AES_128_GCM_SHA256, CipherSuite.TLS_AES_256_GCM_SHA384, CipherSuite.TLS_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, 
/*     */         CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA };
/*     */     RESTRICTED_TLS = (new Builder(true)).cipherSuites(Arrays.<CipherSuite>copyOf(RESTRICTED_CIPHER_SUITES, RESTRICTED_CIPHER_SUITES.length)).tlsVersions(new TlsVersion[] { TlsVersion.TLS_1_3, TlsVersion.TLS_1_2 }).supportsTlsExtensions(true).build();
/*     */     MODERN_TLS = (new Builder(true)).cipherSuites(Arrays.<CipherSuite>copyOf(APPROVED_CIPHER_SUITES, APPROVED_CIPHER_SUITES.length)).tlsVersions(new TlsVersion[] { TlsVersion.TLS_1_3, TlsVersion.TLS_1_2 }).supportsTlsExtensions(true).build();
/*     */     COMPATIBLE_TLS = (new Builder(true)).cipherSuites(Arrays.<CipherSuite>copyOf(APPROVED_CIPHER_SUITES, APPROVED_CIPHER_SUITES.length)).tlsVersions(new TlsVersion[] { TlsVersion.TLS_1_3, TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0 }).supportsTlsExtensions(true).build();
/*     */     CLEARTEXT = (new Builder(false)).build();
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/ConnectionSpec.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */