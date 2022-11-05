/*     */ package okhttp3;
/*     */ 
/*     */ import java.security.cert.Certificate;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.net.ssl.SSLPeerUnverifiedException;
/*     */ import kotlin.Deprecated;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.ReplaceWith;
/*     */ import kotlin.collections.ArraysKt;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.jvm.JvmField;
/*     */ import kotlin.jvm.JvmStatic;
/*     */ import kotlin.jvm.functions.Function0;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.jvm.internal.Lambda;
/*     */ import kotlin.jvm.internal.TypeIntrinsics;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.internal.HostnamesKt;
/*     */ import okhttp3.internal.tls.CertificateChainCleaner;
/*     */ import okio.ByteString;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000T\n\002\030\002\n\002\020\000\n\000\n\002\020\"\n\002\030\002\n\000\n\002\030\002\n\002\b\006\n\002\020\002\n\000\n\002\020\016\n\000\n\002\030\002\n\002\020 \n\002\030\002\n\002\b\002\n\002\020\021\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\003\n\002\020\b\n\002\b\006\030\000 \"2\0020\001:\003!\"#B!\b\000\022\f\020\002\032\b\022\004\022\0020\0040\003\022\n\b\002\020\005\032\004\030\0010\006¢\006\002\020\007J)\020\f\032\0020\r2\006\020\016\032\0020\0172\022\020\020\032\016\022\n\022\b\022\004\022\0020\0230\0220\021H\000¢\006\002\b\024J)\020\f\032\0020\r2\006\020\016\032\0020\0172\022\020\025\032\n\022\006\b\001\022\0020\0270\026\"\0020\027H\007¢\006\002\020\030J\034\020\f\032\0020\r2\006\020\016\032\0020\0172\f\020\025\032\b\022\004\022\0020\0270\022J\023\020\031\032\0020\0322\b\020\033\032\004\030\0010\001H\002J\024\020\034\032\b\022\004\022\0020\0040\0222\006\020\016\032\0020\017J\b\020\035\032\0020\036H\026J\025\020\037\032\0020\0002\006\020\005\032\0020\006H\000¢\006\002\b R\026\020\005\032\004\030\0010\006X\004¢\006\b\n\000\032\004\b\b\020\tR\027\020\002\032\b\022\004\022\0020\0040\003¢\006\b\n\000\032\004\b\n\020\013¨\006$"}, d2 = {"Lokhttp3/CertificatePinner;", "", "pins", "", "Lokhttp3/CertificatePinner$Pin;", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "(Ljava/util/Set;Lokhttp3/internal/tls/CertificateChainCleaner;)V", "getCertificateChainCleaner$okhttp", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "getPins", "()Ljava/util/Set;", "check", "", "hostname", "", "cleanedPeerCertificatesFn", "Lkotlin/Function0;", "", "Ljava/security/cert/X509Certificate;", "check$okhttp", "peerCertificates", "", "Ljava/security/cert/Certificate;", "(Ljava/lang/String;[Ljava/security/cert/Certificate;)V", "equals", "", "other", "findMatchingPins", "hashCode", "", "withCertificateChainCleaner", "withCertificateChainCleaner$okhttp", "Builder", "Companion", "Pin", "okhttp"})
/*     */ public final class CertificatePinner
/*     */ {
/*     */   @NotNull
/*     */   private final Set<Pin> pins;
/*     */   @Nullable
/*     */   private final CertificateChainCleaner certificateChainCleaner;
/*     */   @JvmField
/*     */   @NotNull
/*     */   public static final CertificatePinner DEFAULT;
/*     */   
/*     */   public CertificatePinner(@NotNull Set<Pin> pins, @Nullable CertificateChainCleaner certificateChainCleaner) {
/* 136 */     this.pins = pins; this.certificateChainCleaner = certificateChainCleaner;
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 3, d1 = {"\000\f\n\000\n\002\020 \n\002\030\002\n\000\020\000\032\b\022\004\022\0020\0020\001H\n¢\006\002\b\003"}, d2 = {"<anonymous>", "", "Ljava/security/cert/X509Certificate;", "invoke"})
/*     */   static final class CertificatePinner$check$1
/*     */     extends Lambda
/*     */     implements Function0<List<? extends X509Certificate>>
/*     */   {
/*     */     CertificatePinner$check$1(List param1List, String param1String) {
/*     */       super(0);
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final List<X509Certificate> invoke()
/*     */     {
/* 151 */       if (CertificatePinner.this.getCertificateChainCleaner$okhttp() == null || CertificatePinner.this.getCertificateChainCleaner$okhttp().clean(this.$peerCertificates, this.$hostname) == null) CertificatePinner.this.getCertificateChainCleaner$okhttp().clean(this.$peerCertificates, this.$hostname);  Iterable $this$map$iv = this.$peerCertificates;
/* 152 */       int $i$f$map = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 371 */       Iterable iterable1 = $this$map$iv; Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)); int $i$f$mapTo = 0;
/* 372 */       for (Object item$iv$iv : iterable1)
/* 373 */       { Certificate certificate = (Certificate)item$iv$iv; Collection collection = destination$iv$iv; int $i$a$-map-CertificatePinner$check$1$1 = 0; if (certificate == null)
/* 374 */           throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");  }  return (List<X509Certificate>)destination$iv$iv; } } @NotNull public final List<Pin> findMatchingPins(@NotNull String hostname) { Intrinsics.checkNotNullParameter(hostname, "hostname"); Iterable<Pin> $this$filterList$iv = this.pins; int $i$f$filterList = 0; List<Pin> result$iv = CollectionsKt.emptyList(); for (Pin i$iv : $this$filterList$iv) { Pin $this$filterList = i$iv; int $i$a$-filterList-CertificatePinner$findMatchingPins$1 = 0; if ($this$filterList.matchesHostname(hostname)) { if (result$iv.isEmpty()) { boolean bool = false; result$iv = new ArrayList(); }
/* 375 */          if (result$iv == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableList<T>");  TypeIntrinsics.asMutableList(result$iv).add(i$iv); }
/*     */        }
/*     */     
/* 378 */     return result$iv; }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final Set<Pin> getPins() {
/*     */     return this.pins;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public final CertificateChainCleaner getCertificateChainCleaner$okhttp() {
/*     */     return this.certificateChainCleaner;
/*     */   }
/*     */   
/*     */   public final void check(@NotNull String hostname, @NotNull List peerCertificates) throws SSLPeerUnverifiedException {
/*     */     Intrinsics.checkNotNullParameter(hostname, "hostname");
/*     */     Intrinsics.checkNotNullParameter(peerCertificates, "peerCertificates");
/*     */     check$okhttp(hostname, new CertificatePinner$check$1(peerCertificates, hostname));
/*     */   }
/*     */   
/*     */   public final void check$okhttp(@NotNull String hostname, @NotNull Function0 cleanedPeerCertificatesFn) {
/*     */     Intrinsics.checkNotNullParameter(hostname, "hostname");
/*     */     Intrinsics.checkNotNullParameter(cleanedPeerCertificatesFn, "cleanedPeerCertificatesFn");
/*     */     List<Pin> pins = findMatchingPins(hostname);
/*     */     if (pins.isEmpty())
/*     */       return; 
/*     */     List peerCertificates = (List)cleanedPeerCertificatesFn.invoke();
/*     */     for (X509Certificate peerCertificate : peerCertificates) {
/*     */       ByteString sha1 = (ByteString)null;
/*     */       ByteString sha256 = (ByteString)null;
/*     */       for (Pin pin : pins) {
/*     */         String str = pin.getHashAlgorithm();
/*     */         switch (str.hashCode()) {
/*     */           case 3528965:
/*     */             if (str.equals("sha1")) {
/*     */               if (sha1 == null)
/*     */                 sha1 = Companion.sha1Hash(peerCertificate); 
/*     */               if (Intrinsics.areEqual(pin.getHash(), sha1))
/*     */                 return; 
/*     */               continue;
/*     */             } 
/*     */             break;
/*     */           case -903629273:
/*     */             if (str.equals("sha256")) {
/*     */               if (sha256 == null)
/*     */                 sha256 = Companion.sha256Hash(peerCertificate); 
/*     */               if (Intrinsics.areEqual(pin.getHash(), sha256))
/*     */                 return; 
/*     */               continue;
/*     */             } 
/*     */             break;
/*     */         } 
/*     */         throw new AssertionError("unsupported hashAlgorithm: " + pin.getHashAlgorithm());
/*     */       } 
/*     */     } 
/*     */     boolean bool1 = false, bool2 = false;
/*     */     StringBuilder stringBuilder1 = new StringBuilder();
/*     */     boolean bool3 = false, bool4 = false;
/*     */     StringBuilder $this$buildString = stringBuilder1;
/*     */     int $i$a$-buildString-CertificatePinner$check$message$1 = 0;
/*     */     $this$buildString.append("Certificate pinning failure!");
/*     */     $this$buildString.append("\n  Peer certificate chain:");
/*     */     for (X509Certificate element : peerCertificates) {
/*     */       $this$buildString.append("\n    ");
/*     */       $this$buildString.append(Companion.pin(element));
/*     */       $this$buildString.append(": ");
/*     */       Intrinsics.checkNotNullExpressionValue(element.getSubjectDN(), "element.subjectDN");
/*     */       $this$buildString.append(element.getSubjectDN().getName());
/*     */     } 
/*     */     $this$buildString.append("\n  Pinned certificates for ");
/*     */     $this$buildString.append(hostname);
/*     */     $this$buildString.append(":");
/*     */     for (Pin pin : pins) {
/*     */       $this$buildString.append("\n    ");
/*     */       $this$buildString.append(pin);
/*     */     } 
/*     */     Intrinsics.checkNotNullExpressionValue(stringBuilder1.toString(), "StringBuilder().apply(builderAction).toString()");
/*     */     String message = stringBuilder1.toString();
/*     */     throw (Throwable)new SSLPeerUnverifiedException(message);
/*     */   }
/*     */   
/*     */   @Deprecated(message = "replaced with {@link #check(String, List)}.", replaceWith = @ReplaceWith(imports = {}, expression = "check(hostname, peerCertificates.toList())"))
/*     */   public final void check(@NotNull String hostname, @NotNull Certificate... peerCertificates) throws SSLPeerUnverifiedException {
/*     */     Intrinsics.checkNotNullParameter(hostname, "hostname");
/*     */     Intrinsics.checkNotNullParameter(peerCertificates, "peerCertificates");
/*     */     check(hostname, ArraysKt.toList((Object[])peerCertificates));
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public final CertificatePinner withCertificateChainCleaner$okhttp(@NotNull CertificateChainCleaner certificateChainCleaner) {
/*     */     Intrinsics.checkNotNullParameter(certificateChainCleaner, "certificateChainCleaner");
/*     */     return Intrinsics.areEqual(this.certificateChainCleaner, certificateChainCleaner) ? this : new CertificatePinner(this.pins, certificateChainCleaner);
/*     */   }
/*     */   
/*     */   public boolean equals(@Nullable Object other) {
/*     */     return (other instanceof CertificatePinner && Intrinsics.areEqual(((CertificatePinner)other).pins, this.pins) && Intrinsics.areEqual(((CertificatePinner)other).certificateChainCleaner, this.certificateChainCleaner));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     int result = 37;
/*     */     result = 41 * result + this.pins.hashCode();
/*     */     CertificateChainCleaner certificateChainCleaner = this.certificateChainCleaner;
/*     */     boolean bool = false;
/*     */     result = 41 * result + ((certificateChainCleaner != null) ? certificateChainCleaner.hashCode() : 0);
/*     */     return result;
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0002\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\002\b\003\n\002\030\002\n\002\b\007\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\002\n\002\030\002\n\002\b\004\030\0002\0020\001B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003¢\006\002\020\005J\023\020\016\032\0020\0172\b\020\020\032\004\030\0010\001H\002J\b\020\021\032\0020\022H\026J\016\020\023\032\0020\0172\006\020\024\032\0020\025J\016\020\026\032\0020\0172\006\020\027\032\0020\003J\b\020\030\032\0020\003H\026R\021\020\006\032\0020\007¢\006\b\n\000\032\004\b\b\020\tR\021\020\n\032\0020\003¢\006\b\n\000\032\004\b\013\020\fR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\r\020\f¨\006\031"}, d2 = {"Lokhttp3/CertificatePinner$Pin;", "", "pattern", "", "pin", "(Ljava/lang/String;Ljava/lang/String;)V", "hash", "Lokio/ByteString;", "getHash", "()Lokio/ByteString;", "hashAlgorithm", "getHashAlgorithm", "()Ljava/lang/String;", "getPattern", "equals", "", "other", "hashCode", "", "matchesCertificate", "certificate", "Ljava/security/cert/X509Certificate;", "matchesHostname", "hostname", "toString", "okhttp"})
/*     */   public static final class Pin {
/*     */     @NotNull
/*     */     private final String pattern;
/*     */     @NotNull
/*     */     private final String hashAlgorithm;
/*     */     @NotNull
/*     */     private final ByteString hash;
/*     */     
/*     */     public Pin(@NotNull String pattern, @NotNull String pin) {
/*     */       boolean bool1 = ((StringsKt.startsWith$default(pattern, "*.", false, 2, null) && StringsKt.indexOf$default(pattern, "*", 1, false, 4, null) == -1) || (StringsKt.startsWith$default(pattern, "**.", false, 2, null) && StringsKt.indexOf$default(pattern, "*", 2, false, 4, null) == -1) || StringsKt.indexOf$default(pattern, "*", 0, false, 6, null) == -1) ? true : false;
/*     */       int i = 0;
/*     */       boolean bool2 = false;
/*     */       if (!bool1) {
/*     */         int $i$a$-require-CertificatePinner$Pin$1 = 0;
/*     */         String str = "Unexpected pattern: " + pattern;
/*     */         throw (Throwable)new IllegalArgumentException(str.toString());
/*     */       } 
/*     */       if (HostnamesKt.toCanonicalHost(pattern) != null) {
/*     */         this.pattern = HostnamesKt.toCanonicalHost(pattern);
/*     */         if (StringsKt.startsWith$default(pin, "sha1/", false, 2, null)) {
/*     */           this.hashAlgorithm = "sha1";
/*     */           String str = pin;
/*     */           i = "sha1/".length();
/*     */           bool2 = false;
/*     */           Intrinsics.checkNotNullExpressionValue(str.substring(i), "(this as java.lang.String).substring(startIndex)");
/*     */           if (ByteString.Companion.decodeBase64(str.substring(i)) != null) {
/*     */             this.hash = ByteString.Companion.decodeBase64(str.substring(i));
/*     */             return;
/*     */           } 
/*     */           ByteString.Companion.decodeBase64(str.substring(i));
/*     */           throw (Throwable)new IllegalArgumentException("Invalid pin hash: " + pin);
/*     */         } 
/*     */         if (StringsKt.startsWith$default(pin, "sha256/", false, 2, null)) {
/*     */           this.hashAlgorithm = "sha256";
/*     */           String str = pin;
/*     */           i = "sha256/".length();
/*     */           bool2 = false;
/*     */           Intrinsics.checkNotNullExpressionValue(str.substring(i), "(this as java.lang.String).substring(startIndex)");
/*     */           if (ByteString.Companion.decodeBase64(str.substring(i)) != null) {
/*     */             this.hash = ByteString.Companion.decodeBase64(str.substring(i));
/*     */             return;
/*     */           } 
/*     */           ByteString.Companion.decodeBase64(str.substring(i));
/*     */           throw (Throwable)new IllegalArgumentException("Invalid pin hash: " + pin);
/*     */         } 
/*     */         throw (Throwable)new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': " + pin);
/*     */       } 
/*     */       HostnamesKt.toCanonicalHost(pattern);
/*     */       throw (Throwable)new IllegalArgumentException("Invalid pattern: " + pattern);
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final String getPattern() {
/*     */       return this.pattern;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final String getHashAlgorithm() {
/*     */       return this.hashAlgorithm;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final ByteString getHash() {
/*     */       return this.hash;
/*     */     }
/*     */     
/*     */     public final boolean matchesHostname(@NotNull String hostname) {
/*     */       Intrinsics.checkNotNullParameter(hostname, "hostname");
/*     */       int suffixLength = this.pattern.length() - 3;
/*     */       int prefixLength = hostname.length() - suffixLength;
/*     */       suffixLength = this.pattern.length() - 1;
/*     */       prefixLength = hostname.length() - suffixLength;
/*     */       return StringsKt.startsWith$default(this.pattern, "**.", false, 2, null) ? ((StringsKt.regionMatches$default(hostname, hostname.length() - suffixLength, this.pattern, 3, suffixLength, false, 16, null) && (prefixLength == 0 || hostname.charAt(prefixLength - 1) == '.'))) : (StringsKt.startsWith$default(this.pattern, "*.", false, 2, null) ? ((StringsKt.regionMatches$default(hostname, hostname.length() - suffixLength, this.pattern, 1, suffixLength, false, 16, null) && StringsKt.lastIndexOf$default(hostname, '.', prefixLength - 1, false, 4, null) == -1)) : Intrinsics.areEqual(hostname, this.pattern));
/*     */     }
/*     */     
/*     */     public final boolean matchesCertificate(@NotNull X509Certificate certificate) {
/*     */       Intrinsics.checkNotNullParameter(certificate, "certificate");
/*     */       String str = this.hashAlgorithm;
/*     */       switch (str.hashCode()) {
/*     */         case 3528965:
/*     */           if (str.equals("sha1"));
/*     */           break;
/*     */         case -903629273:
/*     */           if (str.equals("sha256"));
/*     */           break;
/*     */       } 
/*     */       return false;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public String toString() {
/*     */       return this.hashAlgorithm + '/' + this.hash.base64();
/*     */     }
/*     */     
/*     */     public boolean equals(@Nullable Object other) {
/*     */       if (this == other)
/*     */         return true; 
/*     */       if (!(other instanceof Pin))
/*     */         return false; 
/*     */       if ((Intrinsics.areEqual(this.pattern, ((Pin)other).pattern) ^ true) != 0)
/*     */         return false; 
/*     */       if ((Intrinsics.areEqual(this.hashAlgorithm, ((Pin)other).hashAlgorithm) ^ true) != 0)
/*     */         return false; 
/*     */       if ((Intrinsics.areEqual(this.hash, ((Pin)other).hash) ^ true) != 0)
/*     */         return false; 
/*     */       return true;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*     */       int result = this.pattern.hashCode();
/*     */       result = 31 * result + this.hashAlgorithm.hashCode();
/*     */       result = 31 * result + this.hash.hashCode();
/*     */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000*\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020!\n\002\030\002\n\002\b\004\n\002\020\016\n\002\020\021\n\002\b\002\n\002\030\002\n\000\030\0002\0020\001B\005¢\006\002\020\002J'\020\b\032\0020\0002\006\020\t\032\0020\n2\022\020\003\032\n\022\006\b\001\022\0020\n0\013\"\0020\n¢\006\002\020\fJ\006\020\r\032\0020\016R\027\020\003\032\b\022\004\022\0020\0050\004¢\006\b\n\000\032\004\b\006\020\007¨\006\017"}, d2 = {"Lokhttp3/CertificatePinner$Builder;", "", "()V", "pins", "", "Lokhttp3/CertificatePinner$Pin;", "getPins", "()Ljava/util/List;", "add", "pattern", "", "", "(Ljava/lang/String;[Ljava/lang/String;)Lokhttp3/CertificatePinner$Builder;", "build", "Lokhttp3/CertificatePinner;", "okhttp"})
/*     */   public static final class Builder {
/*     */     @NotNull
/*     */     private final List<CertificatePinner.Pin> pins;
/*     */     
/*     */     public Builder() {
/*     */       boolean bool = false;
/*     */       this.pins = new ArrayList<>();
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final List<CertificatePinner.Pin> getPins() {
/*     */       return this.pins;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder add(@NotNull String pattern, @NotNull String... pins) {
/*     */       Intrinsics.checkNotNullParameter(pattern, "pattern");
/*     */       Intrinsics.checkNotNullParameter(pins, "pins");
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-CertificatePinner$Builder$add$1 = 0;
/*     */       for (String pin : pins)
/*     */         $this$apply.pins.add(new CertificatePinner.Pin(pattern, pin)); 
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final CertificatePinner build() {
/*     */       return new CertificatePinner(CollectionsKt.toSet(this.pins), null, 2, null);
/*     */     }
/*     */   }
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   static {
/*     */     DEFAULT = (new Builder()).build();
/*     */   }
/*     */   
/*     */   @JvmStatic
/*     */   @NotNull
/*     */   public static final ByteString sha1Hash(@NotNull X509Certificate $this$sha1Hash) {
/*     */     return Companion.sha1Hash($this$sha1Hash);
/*     */   }
/*     */   
/*     */   @JvmStatic
/*     */   @NotNull
/*     */   public static final ByteString sha256Hash(@NotNull X509Certificate $this$sha256Hash) {
/*     */     return Companion.sha256Hash($this$sha256Hash);
/*     */   }
/*     */   
/*     */   @JvmStatic
/*     */   @NotNull
/*     */   public static final String pin(@NotNull Certificate certificate) {
/*     */     return Companion.pin(certificate);
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000*\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\002\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\020\020\005\032\0020\0062\006\020\007\032\0020\bH\007J\f\020\t\032\0020\n*\0020\013H\007J\f\020\f\032\0020\n*\0020\013H\007R\020\020\003\032\0020\0048\006X\004¢\006\002\n\000¨\006\r"}, d2 = {"Lokhttp3/CertificatePinner$Companion;", "", "()V", "DEFAULT", "Lokhttp3/CertificatePinner;", "pin", "", "certificate", "Ljava/security/cert/Certificate;", "sha1Hash", "Lokio/ByteString;", "Ljava/security/cert/X509Certificate;", "sha256Hash", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */     
/*     */     @JvmStatic
/*     */     @NotNull
/*     */     public final ByteString sha1Hash(@NotNull X509Certificate $this$sha1Hash) {
/*     */       Intrinsics.checkNotNullParameter($this$sha1Hash, "$this$sha1Hash");
/*     */       Intrinsics.checkNotNullExpressionValue($this$sha1Hash.getPublicKey(), "publicKey");
/*     */       Intrinsics.checkNotNullExpressionValue($this$sha1Hash.getPublicKey().getEncoded(), "publicKey.encoded");
/*     */       return ByteString.Companion.of$default(ByteString.Companion, $this$sha1Hash.getPublicKey().getEncoded(), 0, 0, 3, null).sha1();
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @NotNull
/*     */     public final ByteString sha256Hash(@NotNull X509Certificate $this$sha256Hash) {
/*     */       Intrinsics.checkNotNullParameter($this$sha256Hash, "$this$sha256Hash");
/*     */       Intrinsics.checkNotNullExpressionValue($this$sha256Hash.getPublicKey(), "publicKey");
/*     */       Intrinsics.checkNotNullExpressionValue($this$sha256Hash.getPublicKey().getEncoded(), "publicKey.encoded");
/*     */       return ByteString.Companion.of$default(ByteString.Companion, $this$sha256Hash.getPublicKey().getEncoded(), 0, 0, 3, null).sha256();
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @NotNull
/*     */     public final String pin(@NotNull Certificate certificate) {
/*     */       Intrinsics.checkNotNullParameter(certificate, "certificate");
/*     */       boolean bool = certificate instanceof X509Certificate;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       if (!bool) {
/*     */         int $i$a$-require-CertificatePinner$Companion$pin$1 = 0;
/*     */         String str = "Certificate pinning requires X509 certificates";
/*     */         throw (Throwable)new IllegalArgumentException(str.toString());
/*     */       } 
/*     */       return "sha256/" + sha256Hash((X509Certificate)certificate).base64();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/CertificatePinner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */