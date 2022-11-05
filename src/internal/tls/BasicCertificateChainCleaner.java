/*     */ package okhttp3.internal.tls;
/*     */ 
/*     */ import java.security.GeneralSecurityException;
/*     */ import java.security.cert.Certificate;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Deque;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.net.ssl.SSLPeerUnverifiedException;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
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
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000@\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020 \n\002\030\002\n\002\b\002\n\002\020\016\n\000\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\002\b\002\n\002\030\002\n\002\b\003\030\000 \0252\0020\001:\001\025B\r\022\006\020\002\032\0020\003¢\006\002\020\004J$\020\005\032\b\022\004\022\0020\0070\0062\f\020\b\032\b\022\004\022\0020\0070\0062\006\020\t\032\0020\nH\026J\023\020\013\032\0020\f2\b\020\r\032\004\030\0010\016H\002J\b\020\017\032\0020\020H\026J\030\020\021\032\0020\f2\006\020\022\032\0020\0232\006\020\024\032\0020\023H\002R\016\020\002\032\0020\003X\004¢\006\002\n\000¨\006\026"}, d2 = {"Lokhttp3/internal/tls/BasicCertificateChainCleaner;", "Lokhttp3/internal/tls/CertificateChainCleaner;", "trustRootIndex", "Lokhttp3/internal/tls/TrustRootIndex;", "(Lokhttp3/internal/tls/TrustRootIndex;)V", "clean", "", "Ljava/security/cert/Certificate;", "chain", "hostname", "", "equals", "", "other", "", "hashCode", "", "verifySignature", "toVerify", "Ljava/security/cert/X509Certificate;", "signingCert", "Companion", "okhttp"})
/*     */ public final class BasicCertificateChainCleaner
/*     */   extends CertificateChainCleaner
/*     */ {
/*     */   private final TrustRootIndex trustRootIndex;
/*     */   private static final int MAX_SIGNERS = 9;
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   public BasicCertificateChainCleaner(@NotNull TrustRootIndex trustRootIndex) {
/*  38 */     this.trustRootIndex = trustRootIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Certificate> clean(@NotNull List<?> chain, @NotNull String hostname) throws SSLPeerUnverifiedException {
/*  49 */     Intrinsics.checkNotNullParameter(chain, "chain"); Intrinsics.checkNotNullParameter(hostname, "hostname"); Deque queue = new ArrayDeque(chain);
/*  50 */     boolean bool = false; List<Object> result = new ArrayList();
/*  51 */     Intrinsics.checkNotNullExpressionValue(queue.removeFirst(), "queue.removeFirst()"); result.add(queue.removeFirst());
/*  52 */     boolean foundTrustedCertificate = false;
/*     */     
/*     */     byte b1, b2;
/*  55 */     label32: for (b1 = 0, b2 = 9; b1 < b2; b1++) {
/*  56 */       if (result.get(result.size() - 1) == null) throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");  X509Certificate toVerify = (X509Certificate)result.get(result.size() - 1);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  61 */       X509Certificate trustedCert = this.trustRootIndex.findByIssuerAndSignature(toVerify);
/*  62 */       if (trustedCert != null) {
/*  63 */         if (result.size() > 1 || (Intrinsics.areEqual(toVerify, trustedCert) ^ true) != 0) {
/*  64 */           result.add(trustedCert);
/*     */         }
/*  66 */         if (verifySignature(trustedCert, trustedCert)) {
/*  67 */           return (List)result;
/*     */         }
/*  69 */         foundTrustedCertificate = true;
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/*  75 */         Intrinsics.checkNotNullExpressionValue(queue.iterator(), "queue.iterator()"); Iterator i = queue.iterator();
/*  76 */         while (i.hasNext()) {
/*  77 */           if (i.next() == null) throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");  X509Certificate signingCert = (X509Certificate)i.next();
/*  78 */           if (verifySignature(toVerify, signingCert)) {
/*  79 */             i.remove();
/*  80 */             result.add(signingCert);
/*     */             
/*     */             continue label32;
/*     */           } 
/*     */         } 
/*     */         
/*  86 */         if (foundTrustedCertificate) {
/*  87 */           return (List)result;
/*     */         }
/*     */ 
/*     */         
/*  91 */         throw (Throwable)new SSLPeerUnverifiedException(
/*  92 */             "Failed to find a trusted cert that signed " + toVerify);
/*     */       } 
/*     */     } 
/*  95 */     throw (Throwable)new SSLPeerUnverifiedException("Certificate chain too long: " + result);
/*     */   }
/*     */   
/*     */   private final boolean verifySignature(X509Certificate toVerify, X509Certificate signingCert) {
/*     */     boolean bool;
/* 100 */     if ((Intrinsics.areEqual(toVerify.getIssuerDN(), signingCert.getSubjectDN()) ^ true) != 0) {
/* 101 */       return false;
/*     */     }
/*     */     try {
/* 104 */       toVerify.verify(signingCert.getPublicKey());
/* 105 */       bool = true;
/* 106 */     } catch (GeneralSecurityException verifyFailed) {
/* 107 */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   public int hashCode() {
/* 112 */     return this.trustRootIndex.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(@Nullable Object other) {
/* 116 */     return (other == this) ? true : (
/*     */ 
/*     */       
/* 119 */       (other instanceof BasicCertificateChainCleaner && Intrinsics.areEqual(((BasicCertificateChainCleaner)other).trustRootIndex, this.trustRootIndex)));
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\022\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\016\020\003\032\0020\004XT¢\006\002\n\000¨\006\005"}, d2 = {"Lokhttp3/internal/tls/BasicCertificateChainCleaner$Companion;", "", "()V", "MAX_SIGNERS", "", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/tls/BasicCertificateChainCleaner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */