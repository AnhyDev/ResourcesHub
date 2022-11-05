/*     */ package okhttp3;@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\022\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\002\b\006\030\000 \b2\0020\001:\001\bB\017\b\002\022\006\020\002\032\0020\003¢\006\002\020\004J\r\020\002\032\0020\003H\007¢\006\002\b\006J\b\020\007\032\0020\003H\026R\023\020\002\032\0020\0038\007¢\006\b\n\000\032\004\b\002\020\005¨\006\t"}, d2 = {"Lokhttp3/CipherSuite;", "", "javaName", "", "(Ljava/lang/String;)V", "()Ljava/lang/String;", "-deprecated_javaName", "toString", "Companion", "okhttp"}) public final class CipherSuite { @NotNull private final String javaName; @NotNull private static final Comparator<String> ORDER_BY_NAME; private static final Map<String, CipherSuite> INSTANCES; @JvmField @NotNull public static final CipherSuite TLS_RSA_WITH_NULL_MD5; @JvmField @NotNull public static final CipherSuite TLS_RSA_WITH_NULL_SHA; @JvmField @NotNull public static final CipherSuite TLS_RSA_EXPORT_WITH_RC4_40_MD5; @JvmField @NotNull public static final CipherSuite TLS_RSA_WITH_RC4_128_MD5; @JvmField @NotNull public static final CipherSuite TLS_RSA_WITH_RC4_128_SHA; @JvmField @NotNull public static final CipherSuite TLS_RSA_EXPORT_WITH_DES40_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_RSA_WITH_DES_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_RSA_WITH_3DES_EDE_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_DHE_DSS_WITH_DES_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_DHE_RSA_WITH_DES_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_DH_anon_EXPORT_WITH_RC4_40_MD5; @JvmField @NotNull public static final CipherSuite TLS_DH_anon_WITH_RC4_128_MD5; @JvmField @NotNull public static final CipherSuite TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_DH_anon_WITH_DES_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_DH_anon_WITH_3DES_EDE_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_KRB5_WITH_DES_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_KRB5_WITH_3DES_EDE_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_KRB5_WITH_RC4_128_SHA; @JvmField @NotNull public static final CipherSuite TLS_KRB5_WITH_DES_CBC_MD5; @JvmField @NotNull public static final CipherSuite TLS_KRB5_WITH_3DES_EDE_CBC_MD5; @JvmField @NotNull public static final CipherSuite TLS_KRB5_WITH_RC4_128_MD5; @JvmField @NotNull public static final CipherSuite TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA; @JvmField @NotNull public static final CipherSuite TLS_KRB5_EXPORT_WITH_RC4_40_SHA; @JvmField @NotNull public static final CipherSuite TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5; @JvmField @NotNull public static final CipherSuite TLS_KRB5_EXPORT_WITH_RC4_40_MD5; @JvmField @NotNull public static final CipherSuite TLS_RSA_WITH_AES_128_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_DHE_DSS_WITH_AES_128_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_DHE_RSA_WITH_AES_128_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_DH_anon_WITH_AES_128_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_RSA_WITH_AES_256_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_DHE_DSS_WITH_AES_256_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_DHE_RSA_WITH_AES_256_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_DH_anon_WITH_AES_256_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_RSA_WITH_NULL_SHA256; @JvmField @NotNull public static final CipherSuite TLS_RSA_WITH_AES_128_CBC_SHA256; @JvmField @NotNull
/*     */   public static final CipherSuite TLS_RSA_WITH_AES_256_CBC_SHA256; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_DHE_DSS_WITH_AES_128_CBC_SHA256; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_RSA_WITH_CAMELLIA_128_CBC_SHA; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_DHE_RSA_WITH_AES_128_CBC_SHA256; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_DHE_DSS_WITH_AES_256_CBC_SHA256; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_DHE_RSA_WITH_AES_256_CBC_SHA256; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_DH_anon_WITH_AES_128_CBC_SHA256; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_DH_anon_WITH_AES_256_CBC_SHA256; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_RSA_WITH_CAMELLIA_256_CBC_SHA; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_PSK_WITH_RC4_128_SHA; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_PSK_WITH_3DES_EDE_CBC_SHA; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_PSK_WITH_AES_128_CBC_SHA; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_PSK_WITH_AES_256_CBC_SHA; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_RSA_WITH_SEED_CBC_SHA; @JvmField
/*     */   @NotNull
/*  38 */   public static final CipherSuite TLS_RSA_WITH_AES_128_GCM_SHA256; private CipherSuite(String javaName) { this.javaName = javaName; } @JvmField @NotNull public static final CipherSuite TLS_RSA_WITH_AES_256_GCM_SHA384; @JvmField @NotNull public static final CipherSuite TLS_DHE_RSA_WITH_AES_128_GCM_SHA256; @JvmField @NotNull public static final CipherSuite TLS_DHE_RSA_WITH_AES_256_GCM_SHA384; @JvmField @NotNull public static final CipherSuite TLS_DHE_DSS_WITH_AES_128_GCM_SHA256; @JvmField @NotNull public static final CipherSuite TLS_DHE_DSS_WITH_AES_256_GCM_SHA384; @JvmField @NotNull public static final CipherSuite TLS_DH_anon_WITH_AES_128_GCM_SHA256; @JvmField @NotNull public static final CipherSuite TLS_DH_anon_WITH_AES_256_GCM_SHA384; @JvmField @NotNull public static final CipherSuite TLS_EMPTY_RENEGOTIATION_INFO_SCSV; @JvmField @NotNull public static final CipherSuite TLS_FALLBACK_SCSV; @JvmField @NotNull public static final CipherSuite TLS_ECDH_ECDSA_WITH_NULL_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDH_ECDSA_WITH_RC4_128_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_ECDSA_WITH_NULL_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_ECDSA_WITH_RC4_128_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDH_RSA_WITH_NULL_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDH_RSA_WITH_RC4_128_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDH_RSA_WITH_AES_128_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDH_RSA_WITH_AES_256_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_RSA_WITH_NULL_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_RSA_WITH_RC4_128_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA; @JvmField @NotNull
/*     */   public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_ECDH_anon_WITH_NULL_SHA; @JvmName(name = "javaName")
/*     */   @NotNull
/*  45 */   public final String javaName() { return this.javaName; } @JvmField @NotNull public static final CipherSuite TLS_ECDH_anon_WITH_RC4_128_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDH_anon_WITH_AES_128_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDH_anon_WITH_AES_256_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384; @JvmField @NotNull public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256; @JvmField @NotNull public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384; @JvmField @NotNull public static final CipherSuite TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256; @JvmField @NotNull public static final CipherSuite TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384; @JvmField @NotNull public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256; @JvmField @NotNull public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384; @JvmField @NotNull public static final CipherSuite TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256; @JvmField @NotNull public static final CipherSuite TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256; @JvmField @NotNull public static final CipherSuite TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256; @JvmField @NotNull public static final CipherSuite TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256; @JvmField @NotNull public static final CipherSuite TLS_AES_128_GCM_SHA256; @JvmField @NotNull public static final CipherSuite TLS_AES_256_GCM_SHA384; @JvmField @NotNull public static final CipherSuite TLS_CHACHA20_POLY1305_SHA256; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_AES_128_CCM_SHA256; @JvmField
/*     */   @NotNull
/*     */   public static final CipherSuite TLS_AES_128_CCM_8_SHA256; @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "javaName"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_javaName")
/*     */   @NotNull
/*  52 */   public final String -deprecated_javaName() { return this.javaName; } @NotNull
/*     */   public String toString() {
/*  54 */     return this.javaName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public static final Companion Companion = new Companion(null); static { ORDER_BY_NAME = new CipherSuite$Companion$ORDER_BY_NAME$1();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     boolean bool = false; INSTANCES = new LinkedHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     TLS_RSA_WITH_NULL_MD5 = Companion.init("SSL_RSA_WITH_NULL_MD5", 1);
/*  89 */     TLS_RSA_WITH_NULL_SHA = Companion.init("SSL_RSA_WITH_NULL_SHA", 2);
/*  90 */     TLS_RSA_EXPORT_WITH_RC4_40_MD5 = Companion.init("SSL_RSA_EXPORT_WITH_RC4_40_MD5", 3);
/*  91 */     TLS_RSA_WITH_RC4_128_MD5 = Companion.init("SSL_RSA_WITH_RC4_128_MD5", 4);
/*  92 */     TLS_RSA_WITH_RC4_128_SHA = Companion.init("SSL_RSA_WITH_RC4_128_SHA", 5);
/*     */ 
/*     */     
/*  95 */     TLS_RSA_EXPORT_WITH_DES40_CBC_SHA = Companion.init("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA", 8);
/*  96 */     TLS_RSA_WITH_DES_CBC_SHA = Companion.init("SSL_RSA_WITH_DES_CBC_SHA", 9);
/*  97 */     TLS_RSA_WITH_3DES_EDE_CBC_SHA = Companion.init("SSL_RSA_WITH_3DES_EDE_CBC_SHA", 10);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA = Companion.init("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", 17);
/* 105 */     TLS_DHE_DSS_WITH_DES_CBC_SHA = Companion.init("SSL_DHE_DSS_WITH_DES_CBC_SHA", 18);
/* 106 */     TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA = Companion.init("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA", 19);
/* 107 */     TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA = Companion.init("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", 20);
/* 108 */     TLS_DHE_RSA_WITH_DES_CBC_SHA = Companion.init("SSL_DHE_RSA_WITH_DES_CBC_SHA", 21);
/* 109 */     TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA = Companion.init("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA", 22);
/* 110 */     TLS_DH_anon_EXPORT_WITH_RC4_40_MD5 = Companion.init("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5", 23);
/* 111 */     TLS_DH_anon_WITH_RC4_128_MD5 = Companion.init("SSL_DH_anon_WITH_RC4_128_MD5", 24);
/* 112 */     TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA = Companion.init("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA", 25);
/* 113 */     TLS_DH_anon_WITH_DES_CBC_SHA = Companion.init("SSL_DH_anon_WITH_DES_CBC_SHA", 26);
/* 114 */     TLS_DH_anon_WITH_3DES_EDE_CBC_SHA = Companion.init("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA", 27);
/* 115 */     TLS_KRB5_WITH_DES_CBC_SHA = Companion.init("TLS_KRB5_WITH_DES_CBC_SHA", 30);
/* 116 */     TLS_KRB5_WITH_3DES_EDE_CBC_SHA = Companion.init("TLS_KRB5_WITH_3DES_EDE_CBC_SHA", 31);
/* 117 */     TLS_KRB5_WITH_RC4_128_SHA = Companion.init("TLS_KRB5_WITH_RC4_128_SHA", 32);
/*     */     
/* 119 */     TLS_KRB5_WITH_DES_CBC_MD5 = Companion.init("TLS_KRB5_WITH_DES_CBC_MD5", 34);
/* 120 */     TLS_KRB5_WITH_3DES_EDE_CBC_MD5 = Companion.init("TLS_KRB5_WITH_3DES_EDE_CBC_MD5", 35);
/* 121 */     TLS_KRB5_WITH_RC4_128_MD5 = Companion.init("TLS_KRB5_WITH_RC4_128_MD5", 36);
/*     */     
/* 123 */     TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA = Companion.init("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA", 38);
/*     */     
/* 125 */     TLS_KRB5_EXPORT_WITH_RC4_40_SHA = Companion.init("TLS_KRB5_EXPORT_WITH_RC4_40_SHA", 40);
/* 126 */     TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5 = Companion.init("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5", 41);
/*     */     
/* 128 */     TLS_KRB5_EXPORT_WITH_RC4_40_MD5 = Companion.init("TLS_KRB5_EXPORT_WITH_RC4_40_MD5", 43);
/*     */ 
/*     */ 
/*     */     
/* 132 */     TLS_RSA_WITH_AES_128_CBC_SHA = Companion.init("TLS_RSA_WITH_AES_128_CBC_SHA", 47);
/*     */ 
/*     */     
/* 135 */     TLS_DHE_DSS_WITH_AES_128_CBC_SHA = Companion.init("TLS_DHE_DSS_WITH_AES_128_CBC_SHA", 50);
/* 136 */     TLS_DHE_RSA_WITH_AES_128_CBC_SHA = Companion.init("TLS_DHE_RSA_WITH_AES_128_CBC_SHA", 51);
/* 137 */     TLS_DH_anon_WITH_AES_128_CBC_SHA = Companion.init("TLS_DH_anon_WITH_AES_128_CBC_SHA", 52);
/* 138 */     TLS_RSA_WITH_AES_256_CBC_SHA = Companion.init("TLS_RSA_WITH_AES_256_CBC_SHA", 53);
/*     */ 
/*     */     
/* 141 */     TLS_DHE_DSS_WITH_AES_256_CBC_SHA = Companion.init("TLS_DHE_DSS_WITH_AES_256_CBC_SHA", 56);
/* 142 */     TLS_DHE_RSA_WITH_AES_256_CBC_SHA = Companion.init("TLS_DHE_RSA_WITH_AES_256_CBC_SHA", 57);
/* 143 */     TLS_DH_anon_WITH_AES_256_CBC_SHA = Companion.init("TLS_DH_anon_WITH_AES_256_CBC_SHA", 58);
/* 144 */     TLS_RSA_WITH_NULL_SHA256 = Companion.init("TLS_RSA_WITH_NULL_SHA256", 59);
/* 145 */     TLS_RSA_WITH_AES_128_CBC_SHA256 = Companion.init("TLS_RSA_WITH_AES_128_CBC_SHA256", 60);
/* 146 */     TLS_RSA_WITH_AES_256_CBC_SHA256 = Companion.init("TLS_RSA_WITH_AES_256_CBC_SHA256", 61);
/*     */ 
/*     */     
/* 149 */     TLS_DHE_DSS_WITH_AES_128_CBC_SHA256 = Companion.init("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", 64);
/* 150 */     TLS_RSA_WITH_CAMELLIA_128_CBC_SHA = Companion.init("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", 65);
/*     */ 
/*     */     
/* 153 */     TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA = Companion.init("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", 68);
/* 154 */     TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA = Companion.init("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", 69);
/*     */     
/* 156 */     TLS_DHE_RSA_WITH_AES_128_CBC_SHA256 = Companion.init("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", 103);
/*     */ 
/*     */     
/* 159 */     TLS_DHE_DSS_WITH_AES_256_CBC_SHA256 = Companion.init("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", 106);
/* 160 */     TLS_DHE_RSA_WITH_AES_256_CBC_SHA256 = Companion.init("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", 107);
/* 161 */     TLS_DH_anon_WITH_AES_128_CBC_SHA256 = Companion.init("TLS_DH_anon_WITH_AES_128_CBC_SHA256", 108);
/* 162 */     TLS_DH_anon_WITH_AES_256_CBC_SHA256 = Companion.init("TLS_DH_anon_WITH_AES_256_CBC_SHA256", 109);
/* 163 */     TLS_RSA_WITH_CAMELLIA_256_CBC_SHA = Companion.init("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", 132);
/*     */ 
/*     */     
/* 166 */     TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA = Companion.init("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", 135);
/* 167 */     TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA = Companion.init("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", 136);
/*     */     
/* 169 */     TLS_PSK_WITH_RC4_128_SHA = Companion.init("TLS_PSK_WITH_RC4_128_SHA", 138);
/* 170 */     TLS_PSK_WITH_3DES_EDE_CBC_SHA = Companion.init("TLS_PSK_WITH_3DES_EDE_CBC_SHA", 139);
/* 171 */     TLS_PSK_WITH_AES_128_CBC_SHA = Companion.init("TLS_PSK_WITH_AES_128_CBC_SHA", 140);
/* 172 */     TLS_PSK_WITH_AES_256_CBC_SHA = Companion.init("TLS_PSK_WITH_AES_256_CBC_SHA", 141);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     TLS_RSA_WITH_SEED_CBC_SHA = Companion.init("TLS_RSA_WITH_SEED_CBC_SHA", 150);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 187 */     TLS_RSA_WITH_AES_128_GCM_SHA256 = Companion.init("TLS_RSA_WITH_AES_128_GCM_SHA256", 156);
/* 188 */     TLS_RSA_WITH_AES_256_GCM_SHA384 = Companion.init("TLS_RSA_WITH_AES_256_GCM_SHA384", 157);
/* 189 */     TLS_DHE_RSA_WITH_AES_128_GCM_SHA256 = Companion.init("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", 158);
/* 190 */     TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 = Companion.init("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", 159);
/*     */ 
/*     */     
/* 193 */     TLS_DHE_DSS_WITH_AES_128_GCM_SHA256 = Companion.init("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", 162);
/* 194 */     TLS_DHE_DSS_WITH_AES_256_GCM_SHA384 = Companion.init("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", 163);
/*     */ 
/*     */     
/* 197 */     TLS_DH_anon_WITH_AES_128_GCM_SHA256 = Companion.init("TLS_DH_anon_WITH_AES_128_GCM_SHA256", 166);
/* 198 */     TLS_DH_anon_WITH_AES_256_GCM_SHA384 = Companion.init("TLS_DH_anon_WITH_AES_256_GCM_SHA384", 167);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 229 */     TLS_EMPTY_RENEGOTIATION_INFO_SCSV = Companion.init("TLS_EMPTY_RENEGOTIATION_INFO_SCSV", 255);
/* 230 */     TLS_FALLBACK_SCSV = Companion.init("TLS_FALLBACK_SCSV", 22016);
/* 231 */     TLS_ECDH_ECDSA_WITH_NULL_SHA = Companion.init("TLS_ECDH_ECDSA_WITH_NULL_SHA", 49153);
/* 232 */     TLS_ECDH_ECDSA_WITH_RC4_128_SHA = Companion.init("TLS_ECDH_ECDSA_WITH_RC4_128_SHA", 49154);
/* 233 */     TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA = Companion.init("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA", 49155);
/* 234 */     TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA = Companion.init("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA", 49156);
/* 235 */     TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA = Companion.init("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA", 49157);
/* 236 */     TLS_ECDHE_ECDSA_WITH_NULL_SHA = Companion.init("TLS_ECDHE_ECDSA_WITH_NULL_SHA", 49158);
/* 237 */     TLS_ECDHE_ECDSA_WITH_RC4_128_SHA = Companion.init("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA", 49159);
/* 238 */     TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA = Companion.init("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", 49160);
/* 239 */     TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA = Companion.init("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", 49161);
/* 240 */     TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA = Companion.init("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", 49162);
/* 241 */     TLS_ECDH_RSA_WITH_NULL_SHA = Companion.init("TLS_ECDH_RSA_WITH_NULL_SHA", 49163);
/* 242 */     TLS_ECDH_RSA_WITH_RC4_128_SHA = Companion.init("TLS_ECDH_RSA_WITH_RC4_128_SHA", 49164);
/* 243 */     TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA = Companion.init("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA", 49165);
/* 244 */     TLS_ECDH_RSA_WITH_AES_128_CBC_SHA = Companion.init("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA", 49166);
/* 245 */     TLS_ECDH_RSA_WITH_AES_256_CBC_SHA = Companion.init("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA", 49167);
/* 246 */     TLS_ECDHE_RSA_WITH_NULL_SHA = Companion.init("TLS_ECDHE_RSA_WITH_NULL_SHA", 49168);
/* 247 */     TLS_ECDHE_RSA_WITH_RC4_128_SHA = Companion.init("TLS_ECDHE_RSA_WITH_RC4_128_SHA", 49169);
/* 248 */     TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA = Companion.init("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", 49170);
/* 249 */     TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA = Companion.init("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", 49171);
/* 250 */     TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA = Companion.init("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", 49172);
/* 251 */     TLS_ECDH_anon_WITH_NULL_SHA = Companion.init("TLS_ECDH_anon_WITH_NULL_SHA", 49173);
/* 252 */     TLS_ECDH_anon_WITH_RC4_128_SHA = Companion.init("TLS_ECDH_anon_WITH_RC4_128_SHA", 49174);
/* 253 */     TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA = Companion.init("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA", 49175);
/* 254 */     TLS_ECDH_anon_WITH_AES_128_CBC_SHA = Companion.init("TLS_ECDH_anon_WITH_AES_128_CBC_SHA", 49176);
/* 255 */     TLS_ECDH_anon_WITH_AES_256_CBC_SHA = Companion.init("TLS_ECDH_anon_WITH_AES_256_CBC_SHA", 49177);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 265 */     TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 = Companion.init("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", 49187);
/* 266 */     TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 = Companion.init("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", 49188);
/* 267 */     TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 = Companion.init("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256", 49189);
/* 268 */     TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 = Companion.init("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384", 49190);
/* 269 */     TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 = Companion.init("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", 49191);
/* 270 */     TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 = Companion.init("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", 49192);
/* 271 */     TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 = Companion.init("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256", 49193);
/* 272 */     TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 = Companion.init("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384", 49194);
/* 273 */     TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 = Companion.init("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", 49195);
/* 274 */     TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 = Companion.init("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", 49196);
/* 275 */     TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 = Companion.init("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256", 49197);
/* 276 */     TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 = Companion.init("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384", 49198);
/* 277 */     TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 = Companion.init("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", 49199);
/* 278 */     TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 = Companion.init("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", 49200);
/* 279 */     TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 = Companion.init("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256", 49201);
/* 280 */     TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 = Companion.init("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384", 49202);
/*     */ 
/*     */     
/* 283 */     TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA = Companion.init("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA", 49205);
/* 284 */     TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA = Companion.init("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA", 49206);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 406 */     TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 = Companion.init("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52392);
/* 407 */     TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 = Companion.init("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", 52393);
/* 408 */     TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256 = Companion.init("TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52394);
/*     */     
/* 410 */     TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256 = Companion.init("TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256", 52396);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 415 */     TLS_AES_128_GCM_SHA256 = Companion.init("TLS_AES_128_GCM_SHA256", 4865);
/* 416 */     TLS_AES_256_GCM_SHA384 = Companion.init("TLS_AES_256_GCM_SHA384", 4866);
/* 417 */     TLS_CHACHA20_POLY1305_SHA256 = Companion.init("TLS_CHACHA20_POLY1305_SHA256", 4867);
/* 418 */     TLS_AES_128_CCM_SHA256 = Companion.init("TLS_AES_128_CCM_SHA256", 4868);
/* 419 */     TLS_AES_128_CCM_8_SHA256 = Companion.init("TLS_AES_128_CCM_8_SHA256", 4869); } @JvmStatic
/*     */   @NotNull
/*     */   public static final synchronized CipherSuite forJavaName(@NotNull String javaName) { return Companion.forJavaName(javaName); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000.\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020%\n\002\020\016\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b}\n\002\020\b\n\002\b\002\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\022\020\001\032\0020\0062\007\020\001\032\0020\005H\007J\034\020\001\032\0020\0062\007\020\001\032\0020\0052\b\020\001\032\0030\001H\002J\022\020\001\032\0020\0052\007\020\001\032\0020\005H\002R\032\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0060\004X\004¢\006\002\n\000R$\020\007\032\022\022\004\022\0020\0050\bj\b\022\004\022\0020\005`\tX\004¢\006\b\n\000\032\004\b\n\020\013R\020\020\f\032\0020\0068\006X\004¢\006\002\n\000R\020\020\r\032\0020\0068\006X\004¢\006\002\n\000R\020\020\016\032\0020\0068\006X\004¢\006\002\n\000R\020\020\017\032\0020\0068\006X\004¢\006\002\n\000R\020\020\020\032\0020\0068\006X\004¢\006\002\n\000R\020\020\021\032\0020\0068\006X\004¢\006\002\n\000R\020\020\022\032\0020\0068\006X\004¢\006\002\n\000R\020\020\023\032\0020\0068\006X\004¢\006\002\n\000R\020\020\024\032\0020\0068\006X\004¢\006\002\n\000R\020\020\025\032\0020\0068\006X\004¢\006\002\n\000R\020\020\026\032\0020\0068\006X\004¢\006\002\n\000R\020\020\027\032\0020\0068\006X\004¢\006\002\n\000R\020\020\030\032\0020\0068\006X\004¢\006\002\n\000R\020\020\031\032\0020\0068\006X\004¢\006\002\n\000R\020\020\032\032\0020\0068\006X\004¢\006\002\n\000R\020\020\033\032\0020\0068\006X\004¢\006\002\n\000R\020\020\034\032\0020\0068\006X\004¢\006\002\n\000R\020\020\035\032\0020\0068\006X\004¢\006\002\n\000R\020\020\036\032\0020\0068\006X\004¢\006\002\n\000R\020\020\037\032\0020\0068\006X\004¢\006\002\n\000R\020\020 \032\0020\0068\006X\004¢\006\002\n\000R\020\020!\032\0020\0068\006X\004¢\006\002\n\000R\020\020\"\032\0020\0068\006X\004¢\006\002\n\000R\020\020#\032\0020\0068\006X\004¢\006\002\n\000R\020\020$\032\0020\0068\006X\004¢\006\002\n\000R\020\020%\032\0020\0068\006X\004¢\006\002\n\000R\020\020&\032\0020\0068\006X\004¢\006\002\n\000R\020\020'\032\0020\0068\006X\004¢\006\002\n\000R\020\020(\032\0020\0068\006X\004¢\006\002\n\000R\020\020)\032\0020\0068\006X\004¢\006\002\n\000R\020\020*\032\0020\0068\006X\004¢\006\002\n\000R\020\020+\032\0020\0068\006X\004¢\006\002\n\000R\020\020,\032\0020\0068\006X\004¢\006\002\n\000R\020\020-\032\0020\0068\006X\004¢\006\002\n\000R\020\020.\032\0020\0068\006X\004¢\006\002\n\000R\020\020/\032\0020\0068\006X\004¢\006\002\n\000R\020\0200\032\0020\0068\006X\004¢\006\002\n\000R\020\0201\032\0020\0068\006X\004¢\006\002\n\000R\020\0202\032\0020\0068\006X\004¢\006\002\n\000R\020\0203\032\0020\0068\006X\004¢\006\002\n\000R\020\0204\032\0020\0068\006X\004¢\006\002\n\000R\020\0205\032\0020\0068\006X\004¢\006\002\n\000R\020\0206\032\0020\0068\006X\004¢\006\002\n\000R\020\0207\032\0020\0068\006X\004¢\006\002\n\000R\020\0208\032\0020\0068\006X\004¢\006\002\n\000R\020\0209\032\0020\0068\006X\004¢\006\002\n\000R\020\020:\032\0020\0068\006X\004¢\006\002\n\000R\020\020;\032\0020\0068\006X\004¢\006\002\n\000R\020\020<\032\0020\0068\006X\004¢\006\002\n\000R\020\020=\032\0020\0068\006X\004¢\006\002\n\000R\020\020>\032\0020\0068\006X\004¢\006\002\n\000R\020\020?\032\0020\0068\006X\004¢\006\002\n\000R\020\020@\032\0020\0068\006X\004¢\006\002\n\000R\020\020A\032\0020\0068\006X\004¢\006\002\n\000R\020\020B\032\0020\0068\006X\004¢\006\002\n\000R\020\020C\032\0020\0068\006X\004¢\006\002\n\000R\020\020D\032\0020\0068\006X\004¢\006\002\n\000R\020\020E\032\0020\0068\006X\004¢\006\002\n\000R\020\020F\032\0020\0068\006X\004¢\006\002\n\000R\020\020G\032\0020\0068\006X\004¢\006\002\n\000R\020\020H\032\0020\0068\006X\004¢\006\002\n\000R\020\020I\032\0020\0068\006X\004¢\006\002\n\000R\020\020J\032\0020\0068\006X\004¢\006\002\n\000R\020\020K\032\0020\0068\006X\004¢\006\002\n\000R\020\020L\032\0020\0068\006X\004¢\006\002\n\000R\020\020M\032\0020\0068\006X\004¢\006\002\n\000R\020\020N\032\0020\0068\006X\004¢\006\002\n\000R\020\020O\032\0020\0068\006X\004¢\006\002\n\000R\020\020P\032\0020\0068\006X\004¢\006\002\n\000R\020\020Q\032\0020\0068\006X\004¢\006\002\n\000R\020\020R\032\0020\0068\006X\004¢\006\002\n\000R\020\020S\032\0020\0068\006X\004¢\006\002\n\000R\020\020T\032\0020\0068\006X\004¢\006\002\n\000R\020\020U\032\0020\0068\006X\004¢\006\002\n\000R\020\020V\032\0020\0068\006X\004¢\006\002\n\000R\020\020W\032\0020\0068\006X\004¢\006\002\n\000R\020\020X\032\0020\0068\006X\004¢\006\002\n\000R\020\020Y\032\0020\0068\006X\004¢\006\002\n\000R\020\020Z\032\0020\0068\006X\004¢\006\002\n\000R\020\020[\032\0020\0068\006X\004¢\006\002\n\000R\020\020\\\032\0020\0068\006X\004¢\006\002\n\000R\020\020]\032\0020\0068\006X\004¢\006\002\n\000R\020\020^\032\0020\0068\006X\004¢\006\002\n\000R\020\020_\032\0020\0068\006X\004¢\006\002\n\000R\020\020`\032\0020\0068\006X\004¢\006\002\n\000R\020\020a\032\0020\0068\006X\004¢\006\002\n\000R\020\020b\032\0020\0068\006X\004¢\006\002\n\000R\020\020c\032\0020\0068\006X\004¢\006\002\n\000R\020\020d\032\0020\0068\006X\004¢\006\002\n\000R\020\020e\032\0020\0068\006X\004¢\006\002\n\000R\020\020f\032\0020\0068\006X\004¢\006\002\n\000R\020\020g\032\0020\0068\006X\004¢\006\002\n\000R\020\020h\032\0020\0068\006X\004¢\006\002\n\000R\020\020i\032\0020\0068\006X\004¢\006\002\n\000R\020\020j\032\0020\0068\006X\004¢\006\002\n\000R\020\020k\032\0020\0068\006X\004¢\006\002\n\000R\020\020l\032\0020\0068\006X\004¢\006\002\n\000R\020\020m\032\0020\0068\006X\004¢\006\002\n\000R\020\020n\032\0020\0068\006X\004¢\006\002\n\000R\020\020o\032\0020\0068\006X\004¢\006\002\n\000R\020\020p\032\0020\0068\006X\004¢\006\002\n\000R\020\020q\032\0020\0068\006X\004¢\006\002\n\000R\020\020r\032\0020\0068\006X\004¢\006\002\n\000R\020\020s\032\0020\0068\006X\004¢\006\002\n\000R\020\020t\032\0020\0068\006X\004¢\006\002\n\000R\020\020u\032\0020\0068\006X\004¢\006\002\n\000R\020\020v\032\0020\0068\006X\004¢\006\002\n\000R\020\020w\032\0020\0068\006X\004¢\006\002\n\000R\020\020x\032\0020\0068\006X\004¢\006\002\n\000R\020\020y\032\0020\0068\006X\004¢\006\002\n\000R\020\020z\032\0020\0068\006X\004¢\006\002\n\000R\020\020{\032\0020\0068\006X\004¢\006\002\n\000R\020\020|\032\0020\0068\006X\004¢\006\002\n\000R\020\020}\032\0020\0068\006X\004¢\006\002\n\000R\020\020~\032\0020\0068\006X\004¢\006\002\n\000R\020\020\032\0020\0068\006X\004¢\006\002\n\000R\021\020\001\032\0020\0068\006X\004¢\006\002\n\000R\021\020\001\032\0020\0068\006X\004¢\006\002\n\000R\021\020\001\032\0020\0068\006X\004¢\006\002\n\000¨\006\001"}, d2 = {"Lokhttp3/CipherSuite$Companion;", "", "()V", "INSTANCES", "", "", "Lokhttp3/CipherSuite;", "ORDER_BY_NAME", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "getORDER_BY_NAME$okhttp", "()Ljava/util/Comparator;", "TLS_AES_128_CCM_8_SHA256", "TLS_AES_128_CCM_SHA256", "TLS_AES_128_GCM_SHA256", "TLS_AES_256_GCM_SHA384", "TLS_CHACHA20_POLY1305_SHA256", "TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", "TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA", "TLS_DHE_DSS_WITH_AES_128_CBC_SHA", "TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", "TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", "TLS_DHE_DSS_WITH_AES_256_CBC_SHA", "TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", "TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", "TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", "TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", "TLS_DHE_DSS_WITH_DES_CBC_SHA", "TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", "TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_DHE_RSA_WITH_AES_128_CBC_SHA", "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_DHE_RSA_WITH_AES_256_CBC_SHA", "TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", "TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", "TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", "TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", "TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256", "TLS_DHE_RSA_WITH_DES_CBC_SHA", "TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA", "TLS_DH_anon_EXPORT_WITH_RC4_40_MD5", "TLS_DH_anon_WITH_3DES_EDE_CBC_SHA", "TLS_DH_anon_WITH_AES_128_CBC_SHA", "TLS_DH_anon_WITH_AES_128_CBC_SHA256", "TLS_DH_anon_WITH_AES_128_GCM_SHA256", "TLS_DH_anon_WITH_AES_256_CBC_SHA", "TLS_DH_anon_WITH_AES_256_CBC_SHA256", "TLS_DH_anon_WITH_AES_256_GCM_SHA384", "TLS_DH_anon_WITH_DES_CBC_SHA", "TLS_DH_anon_WITH_RC4_128_MD5", "TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", "TLS_ECDHE_ECDSA_WITH_NULL_SHA", "TLS_ECDHE_ECDSA_WITH_RC4_128_SHA", "TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA", "TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA", "TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256", "TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", "TLS_ECDHE_RSA_WITH_NULL_SHA", "TLS_ECDHE_RSA_WITH_RC4_128_SHA", "TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA", "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA", "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256", "TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256", "TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA", "TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384", "TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384", "TLS_ECDH_ECDSA_WITH_NULL_SHA", "TLS_ECDH_ECDSA_WITH_RC4_128_SHA", "TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA", "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256", "TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256", "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA", "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384", "TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384", "TLS_ECDH_RSA_WITH_NULL_SHA", "TLS_ECDH_RSA_WITH_RC4_128_SHA", "TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA", "TLS_ECDH_anon_WITH_AES_128_CBC_SHA", "TLS_ECDH_anon_WITH_AES_256_CBC_SHA", "TLS_ECDH_anon_WITH_NULL_SHA", "TLS_ECDH_anon_WITH_RC4_128_SHA", "TLS_EMPTY_RENEGOTIATION_INFO_SCSV", "TLS_FALLBACK_SCSV", "TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5", "TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA", "TLS_KRB5_EXPORT_WITH_RC4_40_MD5", "TLS_KRB5_EXPORT_WITH_RC4_40_SHA", "TLS_KRB5_WITH_3DES_EDE_CBC_MD5", "TLS_KRB5_WITH_3DES_EDE_CBC_SHA", "TLS_KRB5_WITH_DES_CBC_MD5", "TLS_KRB5_WITH_DES_CBC_SHA", "TLS_KRB5_WITH_RC4_128_MD5", "TLS_KRB5_WITH_RC4_128_SHA", "TLS_PSK_WITH_3DES_EDE_CBC_SHA", "TLS_PSK_WITH_AES_128_CBC_SHA", "TLS_PSK_WITH_AES_256_CBC_SHA", "TLS_PSK_WITH_RC4_128_SHA", "TLS_RSA_EXPORT_WITH_DES40_CBC_SHA", "TLS_RSA_EXPORT_WITH_RC4_40_MD5", "TLS_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_RSA_WITH_AES_128_CBC_SHA", "TLS_RSA_WITH_AES_128_CBC_SHA256", "TLS_RSA_WITH_AES_128_GCM_SHA256", "TLS_RSA_WITH_AES_256_CBC_SHA", "TLS_RSA_WITH_AES_256_CBC_SHA256", "TLS_RSA_WITH_AES_256_GCM_SHA384", "TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", "TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", "TLS_RSA_WITH_DES_CBC_SHA", "TLS_RSA_WITH_NULL_MD5", "TLS_RSA_WITH_NULL_SHA", "TLS_RSA_WITH_NULL_SHA256", "TLS_RSA_WITH_RC4_128_MD5", "TLS_RSA_WITH_RC4_128_SHA", "TLS_RSA_WITH_SEED_CBC_SHA", "forJavaName", "javaName", "init", "value", "", "secondaryName", "okhttp"})
/*     */   public static final class Companion { private Companion() {}
/*     */     @NotNull
/*     */     public final Comparator<String> getORDER_BY_NAME$okhttp() { return CipherSuite.ORDER_BY_NAME; }
/*     */     @JvmStatic
/*     */     @NotNull
/* 427 */     public final synchronized CipherSuite forJavaName(@NotNull String javaName) { Intrinsics.checkNotNullParameter(javaName, "javaName"); CipherSuite result = (CipherSuite)CipherSuite.INSTANCES.get(javaName);
/* 428 */       if (result == null) {
/* 429 */         result = (CipherSuite)CipherSuite.INSTANCES.get(secondaryName(javaName));
/*     */         
/* 431 */         if (result == null) {
/* 432 */           result = new CipherSuite(javaName, null);
/*     */         }
/*     */ 
/*     */         
/* 436 */         CipherSuite.INSTANCES.put(javaName, result);
/*     */       } 
/* 438 */       return result; }
/*     */ 
/*     */ 
/*     */     
/*     */     private final String secondaryName(String javaName) {
/* 443 */       String str = javaName; byte b = 4; boolean bool = false; if (str == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str.substring(b), "(this as java.lang.String).substring(startIndex)");
/* 444 */       str = javaName; b = 4; bool = false; if (str == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str.substring(b), "(this as java.lang.String).substring(startIndex)"); return StringsKt.startsWith$default(javaName, "TLS_", false, 2, null) ? ("SSL_" + str.substring(b)) : (StringsKt.startsWith$default(javaName, "SSL_", false, 2, null) ? ("TLS_" + str.substring(b)) : 
/* 445 */         javaName);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final CipherSuite init(String javaName, int value) {
/* 455 */       CipherSuite suite = new CipherSuite(javaName, null);
/* 456 */       CipherSuite.INSTANCES.put(javaName, suite);
/* 457 */       return suite;
/*     */     } }
/*     */ 
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\033\n\000\n\002\030\002\n\002\020\016\n\002\030\002\n\000\n\002\020\b\n\002\b\003*\001\000\b\n\030\0002\022\022\004\022\0020\0020\001j\b\022\004\022\0020\002`\003J\030\020\004\032\0020\0052\006\020\006\032\0020\0022\006\020\007\032\0020\002H\026¨\006\b"}, d2 = {"okhttp3/CipherSuite$Companion$ORDER_BY_NAME$1", "Ljava/util/Comparator;", "", "Lkotlin/Comparator;", "compare", "", "a", "b", "okhttp"})
/*     */   public static final class CipherSuite$Companion$ORDER_BY_NAME$1 implements Comparator<String> {
/*     */     public int compare(@NotNull String a, @NotNull String b) {
/*     */       Intrinsics.checkNotNullParameter(a, "a");
/*     */       Intrinsics.checkNotNullParameter(b, "b");
/*     */       int i = 4;
/*     */       int j = a.length(), k = b.length();
/*     */       boolean bool = false;
/*     */       int limit = Math.min(j, k);
/*     */       while (i < limit) {
/*     */         char charA = a.charAt(i);
/*     */         char charB = b.charAt(i);
/*     */         if (charA != charB)
/*     */           return (Intrinsics.compare(charA, charB) < 0) ? -1 : 1; 
/*     */         i++;
/*     */       } 
/*     */       int lengthA = a.length();
/*     */       int lengthB = b.length();
/*     */       if (lengthA != lengthB)
/*     */         return (lengthA < lengthB) ? -1 : 1; 
/*     */       return 0;
/*     */     }
/*     */   } }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/CipherSuite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */