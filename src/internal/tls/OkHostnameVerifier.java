/*     */ package okhttp3.internal.tls;
/*     */ 
/*     */ import java.security.cert.CertificateParsingException;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import javax.net.ssl.HostnameVerifier;
/*     */ import javax.net.ssl.SSLException;
/*     */ import javax.net.ssl.SSLSession;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.internal.Util;
/*     */ import okio.Utf8;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0006\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\002\n\002\020 \n\002\020\016\n\000\n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\b\bÆ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J\024\020\006\032\b\022\004\022\0020\b0\0072\006\020\t\032\0020\nJ\036\020\013\032\b\022\004\022\0020\b0\0072\006\020\t\032\0020\n2\006\020\f\032\0020\004H\002J\026\020\r\032\0020\0162\006\020\017\032\0020\b2\006\020\t\032\0020\nJ\030\020\r\032\0020\0162\006\020\017\032\0020\b2\006\020\020\032\0020\021H\026J\030\020\022\032\0020\0162\006\020\023\032\0020\b2\006\020\t\032\0020\nH\002J\034\020\022\032\0020\0162\b\020\023\032\004\030\0010\b2\b\020\024\032\004\030\0010\bH\002J\030\020\025\032\0020\0162\006\020\026\032\0020\b2\006\020\t\032\0020\nH\002J\f\020\027\032\0020\b*\0020\bH\002J\f\020\030\032\0020\016*\0020\bH\002R\016\020\003\032\0020\004XT¢\006\002\n\000R\016\020\005\032\0020\004XT¢\006\002\n\000¨\006\031"}, d2 = {"Lokhttp3/internal/tls/OkHostnameVerifier;", "Ljavax/net/ssl/HostnameVerifier;", "()V", "ALT_DNS_NAME", "", "ALT_IPA_NAME", "allSubjectAltNames", "", "", "certificate", "Ljava/security/cert/X509Certificate;", "getSubjectAltNames", "type", "verify", "", "host", "session", "Ljavax/net/ssl/SSLSession;", "verifyHostname", "hostname", "pattern", "verifyIpAddress", "ipAddress", "asciiToLowercase", "isAscii", "okhttp"})
/*     */ public final class OkHostnameVerifier
/*     */   implements HostnameVerifier
/*     */ {
/*     */   private static final int ALT_DNS_NAME = 2;
/*     */   private static final int ALT_IPA_NAME = 7;
/*     */   public static final OkHostnameVerifier INSTANCE;
/*     */   
/*     */   static {
/*  35 */     OkHostnameVerifier okHostnameVerifier = new OkHostnameVerifier();
/*     */   }
/*     */   
/*     */   public boolean verify(@NotNull String host, @NotNull SSLSession session) {
/*     */     boolean bool;
/*  40 */     Intrinsics.checkNotNullParameter(host, "host"); Intrinsics.checkNotNullParameter(session, "session");
/*     */ 
/*     */     
/*     */     try {
/*  44 */       if (session.getPeerCertificates()[0] == null) throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");  bool = verify(host, (X509Certificate)session.getPeerCertificates()[0]);
/*  45 */     } catch (SSLException _) {
/*  46 */       bool = false;
/*     */     } 
/*     */     return !isAscii(host) ? false : bool;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean verify(@NotNull String host, @NotNull X509Certificate certificate) {
/*  53 */     Intrinsics.checkNotNullParameter(host, "host"); Intrinsics.checkNotNullParameter(certificate, "certificate"); return 
/*  54 */       Util.canParseAsIpAddress(host) ? verifyIpAddress(host, certificate) : 
/*  55 */       verifyHostname(host, certificate);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean verifyIpAddress(String ipAddress, X509Certificate certificate) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokestatic toCanonicalHost : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   4: astore_3
/*     */     //   5: aload_0
/*     */     //   6: aload_2
/*     */     //   7: bipush #7
/*     */     //   9: invokespecial getSubjectAltNames : (Ljava/security/cert/X509Certificate;I)Ljava/util/List;
/*     */     //   12: checkcast java/lang/Iterable
/*     */     //   15: astore #4
/*     */     //   17: iconst_0
/*     */     //   18: istore #5
/*     */     //   20: aload #4
/*     */     //   22: instanceof java/util/Collection
/*     */     //   25: ifeq -> 45
/*     */     //   28: aload #4
/*     */     //   30: checkcast java/util/Collection
/*     */     //   33: invokeinterface isEmpty : ()Z
/*     */     //   38: ifeq -> 45
/*     */     //   41: iconst_0
/*     */     //   42: goto -> 100
/*     */     //   45: aload #4
/*     */     //   47: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   52: astore #6
/*     */     //   54: aload #6
/*     */     //   56: invokeinterface hasNext : ()Z
/*     */     //   61: ifeq -> 99
/*     */     //   64: aload #6
/*     */     //   66: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   71: astore #7
/*     */     //   73: aload #7
/*     */     //   75: checkcast java/lang/String
/*     */     //   78: astore #8
/*     */     //   80: iconst_0
/*     */     //   81: istore #9
/*     */     //   83: aload_3
/*     */     //   84: aload #8
/*     */     //   86: invokestatic toCanonicalHost : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   89: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   92: ifeq -> 54
/*     */     //   95: iconst_1
/*     */     //   96: goto -> 100
/*     */     //   99: iconst_0
/*     */     //   100: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #61	-> 0
/*     */     //   #63	-> 5
/*     */     //   #209	-> 20
/*     */     //   #210	-> 45
/*     */     //   #210	-> 54
/*     */     //   #64	-> 83
/*     */     //   #211	-> 99
/*     */     //   #63	-> 100
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   80	12	8	it	Ljava/lang/String;
/*     */     //   83	9	9	$i$a$-any-OkHostnameVerifier$verifyIpAddress$1	I
/*     */     //   73	26	7	element$iv	Ljava/lang/Object;
/*     */     //   17	83	4	$this$any$iv	Ljava/lang/Iterable;
/*     */     //   20	80	5	$i$f$any	I
/*     */     //   5	96	3	canonicalIpAddress	Ljava/lang/String;
/*     */     //   0	101	0	this	Lokhttp3/internal/tls/OkHostnameVerifier;
/*     */     //   0	101	1	ipAddress	Ljava/lang/String;
/*     */     //   0	101	2	certificate	Ljava/security/cert/X509Certificate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean verifyHostname(String hostname, X509Certificate certificate) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: invokespecial asciiToLowercase : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   5: astore_3
/*     */     //   6: aload_0
/*     */     //   7: aload_2
/*     */     //   8: iconst_2
/*     */     //   9: invokespecial getSubjectAltNames : (Ljava/security/cert/X509Certificate;I)Ljava/util/List;
/*     */     //   12: checkcast java/lang/Iterable
/*     */     //   15: astore #4
/*     */     //   17: iconst_0
/*     */     //   18: istore #5
/*     */     //   20: aload #4
/*     */     //   22: instanceof java/util/Collection
/*     */     //   25: ifeq -> 45
/*     */     //   28: aload #4
/*     */     //   30: checkcast java/util/Collection
/*     */     //   33: invokeinterface isEmpty : ()Z
/*     */     //   38: ifeq -> 45
/*     */     //   41: iconst_0
/*     */     //   42: goto -> 100
/*     */     //   45: aload #4
/*     */     //   47: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   52: astore #6
/*     */     //   54: aload #6
/*     */     //   56: invokeinterface hasNext : ()Z
/*     */     //   61: ifeq -> 99
/*     */     //   64: aload #6
/*     */     //   66: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   71: astore #7
/*     */     //   73: aload #7
/*     */     //   75: checkcast java/lang/String
/*     */     //   78: astore #8
/*     */     //   80: iconst_0
/*     */     //   81: istore #9
/*     */     //   83: getstatic okhttp3/internal/tls/OkHostnameVerifier.INSTANCE : Lokhttp3/internal/tls/OkHostnameVerifier;
/*     */     //   86: aload_3
/*     */     //   87: aload #8
/*     */     //   89: invokespecial verifyHostname : (Ljava/lang/String;Ljava/lang/String;)Z
/*     */     //   92: ifeq -> 54
/*     */     //   95: iconst_1
/*     */     //   96: goto -> 100
/*     */     //   99: iconst_0
/*     */     //   100: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #70	-> 0
/*     */     //   #71	-> 6
/*     */     //   #212	-> 20
/*     */     //   #213	-> 45
/*     */     //   #213	-> 54
/*     */     //   #72	-> 83
/*     */     //   #214	-> 99
/*     */     //   #71	-> 100
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   80	12	8	it	Ljava/lang/String;
/*     */     //   83	9	9	$i$a$-any-OkHostnameVerifier$verifyHostname$1	I
/*     */     //   73	26	7	element$iv	Ljava/lang/Object;
/*     */     //   17	83	4	$this$any$iv	Ljava/lang/Iterable;
/*     */     //   20	80	5	$i$f$any	I
/*     */     //   6	95	3	hostname	Ljava/lang/String;
/*     */     //   0	101	0	this	Lokhttp3/internal/tls/OkHostnameVerifier;
/*     */     //   0	101	1	hostname	Ljava/lang/String;
/*     */     //   0	101	2	certificate	Ljava/security/cert/X509Certificate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final String asciiToLowercase(String $this$asciiToLowercase) {
/*  83 */     if (isAscii($this$asciiToLowercase)) { String str = $this$asciiToLowercase; Intrinsics.checkNotNullExpressionValue(Locale.US, "Locale.US"); Locale locale = Locale.US; boolean bool = false; if (str == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str.toLowerCase(locale), "(this as java.lang.String).toLowerCase(locale)"); } else {  }
/*  84 */      return $this$asciiToLowercase;
/*     */   }
/*     */ 
/*     */   
/*     */   private final boolean isAscii(String $this$isAscii) {
/*  89 */     return ($this$isAscii.length() == (int)Utf8.size$default($this$isAscii, 0, 0, 3, null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean verifyHostname(String hostname, String pattern) {
/*  99 */     String str1 = hostname;
/* 100 */     String str2 = pattern;
/*     */     
/* 102 */     String str3 = str1; boolean bool1 = false, bool2 = false; if (((str3 == null || str3.length() == 0)) || 
/* 103 */       StringsKt.startsWith$default(str1, ".", false, 2, null) || 
/* 104 */       StringsKt.endsWith$default(str1, "..", false, 2, null))
/*     */     {
/* 106 */       return false;
/*     */     }
/* 108 */     str3 = str2; bool1 = false; bool2 = false; if (((str3 == null || str3.length() == 0)) || 
/* 109 */       StringsKt.startsWith$default(str2, ".", false, 2, null) || 
/* 110 */       StringsKt.endsWith$default(str2, "..", false, 2, null))
/*     */     {
/* 112 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     if (!StringsKt.endsWith$default(str1, ".", false, 2, null)) {
/* 125 */       str1 = str1 + ".";
/*     */     }
/* 127 */     if (!StringsKt.endsWith$default(str2, ".", false, 2, null)) {
/* 128 */       str2 = str2 + ".";
/*     */     }
/*     */ 
/*     */     
/* 132 */     str2 = asciiToLowercase(str2);
/*     */ 
/*     */     
/* 135 */     if (!StringsKt.contains$default(str2, "*", false, 2, null))
/*     */     {
/* 137 */       return Intrinsics.areEqual(str1, str2);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 152 */     if (!StringsKt.startsWith$default(str2, "*.", false, 2, null) || StringsKt.indexOf$default(str2, '*', 1, false, 4, null) != -1)
/*     */     {
/*     */       
/* 155 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     if (str1.length() < str2.length()) {
/* 162 */       return false;
/*     */     }
/*     */     
/* 165 */     if (Intrinsics.areEqual("*.", str2)) {
/* 166 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 170 */     String str4 = str2; bool2 = true; boolean bool3 = false; if (str4 == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str4.substring(bool2), "(this as java.lang.String).substring(startIndex)"); String suffix = str4.substring(bool2);
/* 171 */     if (!StringsKt.endsWith$default(str1, suffix, false, 2, null)) {
/* 172 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 176 */     int suffixStartIndexInHostname = str1.length() - suffix.length();
/*     */     
/* 178 */     if (suffixStartIndexInHostname > 0 && StringsKt.lastIndexOf$default(str1, '.', suffixStartIndexInHostname - 1, false, 4, null) != -1) {
/* 179 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 183 */     return true;
/*     */   }
/*     */   @NotNull
/*     */   public final List<String> allSubjectAltNames(@NotNull X509Certificate certificate) {
/* 187 */     Intrinsics.checkNotNullParameter(certificate, "certificate"); List<String> altIpaNames = getSubjectAltNames(certificate, 7);
/* 188 */     List<String> altDnsNames = getSubjectAltNames(certificate, 2);
/* 189 */     return CollectionsKt.plus(altIpaNames, altDnsNames);
/*     */   }
/*     */   
/*     */   private final List<String> getSubjectAltNames(X509Certificate certificate, int type) {
/*     */     try {
/* 194 */       if (certificate.getSubjectAlternativeNames() != null) { Collection<List<?>> subjectAltNames = certificate.getSubjectAlternativeNames();
/* 195 */         boolean bool = false; List<String> result = new ArrayList();
/* 196 */         for (List<?> subjectAltName : subjectAltNames) {
/* 197 */           if (subjectAltName == null || subjectAltName.size() < 2 || (
/* 198 */             Intrinsics.areEqual(subjectAltName.get(0), Integer.valueOf(type)) ^ true) != 0)
/* 199 */             continue;  if (subjectAltName.get(1) != null) { Object altName = subjectAltName.get(1);
/* 200 */             if (altName == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.String");  result.add((String)altName); continue; }
/*     */            subjectAltName.get(1);
/* 202 */         }  return result; }  certificate.getSubjectAlternativeNames(); return CollectionsKt.emptyList();
/* 203 */     } catch (CertificateParsingException _) {
/* 204 */       return CollectionsKt.emptyList();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/tls/OkHostnameVerifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */