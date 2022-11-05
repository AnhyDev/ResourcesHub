/*    */ package okhttp3.internal.tls;
/*    */ 
/*    */ import java.security.cert.X509Certificate;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.LinkedHashSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import javax.security.auth.x500.X500Principal;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0008\n\002\030\002\n\002\030\002\n\000\n\002\020\021\n\002\030\002\n\002\b\002\n\002\020$\n\002\030\002\n\002\020\"\n\000\n\002\020\013\n\000\n\002\020\000\n\002\b\003\n\002\020\b\n\000\030\0002\0020\001B\031\022\022\020\002\032\n\022\006\b\001\022\0020\0040\003\"\0020\004¢\006\002\020\005J\023\020\n\032\0020\0132\b\020\f\032\004\030\0010\rH\002J\022\020\016\032\004\030\0010\0042\006\020\017\032\0020\004H\026J\b\020\020\032\0020\021H\026R \020\006\032\024\022\004\022\0020\b\022\n\022\b\022\004\022\0020\0040\t0\007X\004¢\006\002\n\000¨\006\022"}, d2 = {"Lokhttp3/internal/tls/BasicTrustRootIndex;", "Lokhttp3/internal/tls/TrustRootIndex;", "caCerts", "", "Ljava/security/cert/X509Certificate;", "([Ljava/security/cert/X509Certificate;)V", "subjectToCaCerts", "", "Ljavax/security/auth/x500/X500Principal;", "", "equals", "", "other", "", "findByIssuerAndSignature", "cert", "hashCode", "", "okhttp"})
/*    */ public final class BasicTrustRootIndex
/*    */   implements TrustRootIndex
/*    */ {
/*    */   private final Map<X500Principal, Set<X509Certificate>> subjectToCaCerts;
/*    */   
/*    */   public BasicTrustRootIndex(@NotNull X509Certificate... caCerts) {
/* 26 */     boolean bool = false; Map<Object, Object> map = new LinkedHashMap<>();
/* 27 */     X509Certificate[] arrayOfX509Certificate = caCerts; int i = arrayOfX509Certificate.length; byte b = 0; while (true) { X509Certificate caCert; if (b < i) { caCert = arrayOfX509Certificate[b];
/* 28 */         Map<Object, Object> map1 = map; Intrinsics.checkNotNullExpressionValue(caCert.getSubjectX500Principal(), "caCert.subjectX500Principal"); Object key$iv = caCert.getSubjectX500Principal(); int $i$f$getOrPut = 0;
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
/* 59 */         Object value$iv = map1.get(key$iv);
/* 60 */         if (value$iv == null) {
/* 61 */           int $i$a$-getOrPut-BasicTrustRootIndex$1 = 0; boolean bool1 = false; Object answer$iv = new LinkedHashSet();
/* 62 */           map1.put(key$iv, answer$iv);
/*    */         }  }
/*    */       else { break; }
/* 65 */        ((Set<X509Certificate>)value$iv).add(caCert);
/*    */       b++; }
/*    */     
/*    */     this.subjectToCaCerts = (Map)map;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public X509Certificate findByIssuerAndSignature(@NotNull X509Certificate cert) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: ldc 'cert'
/*    */     //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*    */     //   6: aload_1
/*    */     //   7: invokevirtual getIssuerX500Principal : ()Ljavax/security/auth/x500/X500Principal;
/*    */     //   10: astore_2
/*    */     //   11: aload_0
/*    */     //   12: getfield subjectToCaCerts : Ljava/util/Map;
/*    */     //   15: aload_2
/*    */     //   16: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
/*    */     //   21: checkcast java/util/Set
/*    */     //   24: dup
/*    */     //   25: ifnull -> 31
/*    */     //   28: goto -> 34
/*    */     //   31: pop
/*    */     //   32: aconst_null
/*    */     //   33: areturn
/*    */     //   34: astore_3
/*    */     //   35: aload_3
/*    */     //   36: checkcast java/lang/Iterable
/*    */     //   39: astore #4
/*    */     //   41: iconst_0
/*    */     //   42: istore #5
/*    */     //   44: aload #4
/*    */     //   46: invokeinterface iterator : ()Ljava/util/Iterator;
/*    */     //   51: astore #6
/*    */     //   53: aload #6
/*    */     //   55: invokeinterface hasNext : ()Z
/*    */     //   60: ifeq -> 107
/*    */     //   63: aload #6
/*    */     //   65: invokeinterface next : ()Ljava/lang/Object;
/*    */     //   70: astore #7
/*    */     //   72: aload #7
/*    */     //   74: checkcast java/security/cert/X509Certificate
/*    */     //   77: astore #8
/*    */     //   79: iconst_0
/*    */     //   80: istore #9
/*    */     //   82: nop
/*    */     //   83: aload_1
/*    */     //   84: aload #8
/*    */     //   86: invokevirtual getPublicKey : ()Ljava/security/PublicKey;
/*    */     //   89: invokevirtual verify : (Ljava/security/PublicKey;)V
/*    */     //   92: iconst_1
/*    */     //   93: goto -> 99
/*    */     //   96: astore #10
/*    */     //   98: iconst_0
/*    */     //   99: ifeq -> 53
/*    */     //   102: aload #7
/*    */     //   104: goto -> 108
/*    */     //   107: aconst_null
/*    */     //   108: checkcast java/security/cert/X509Certificate
/*    */     //   111: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #34	-> 6
/*    */     //   #35	-> 11
/*    */     //   #35	-> 31
/*    */     //   #37	-> 35
/*    */     //   #57	-> 44
/*    */     //   #57	-> 53
/*    */     //   #38	-> 82
/*    */     //   #39	-> 83
/*    */     //   #40	-> 92
/*    */     //   #41	-> 96
/*    */     //   #42	-> 98
/*    */     //   #58	-> 107
/*    */     //   #37	-> 111
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   98	1	10	_	Ljava/lang/Exception;
/*    */     //   79	20	8	it	Ljava/security/cert/X509Certificate;
/*    */     //   82	17	9	$i$a$-firstOrNull-BasicTrustRootIndex$findByIssuerAndSignature$1	I
/*    */     //   72	35	7	element$iv	Ljava/lang/Object;
/*    */     //   41	67	4	$this$firstOrNull$iv	Ljava/lang/Iterable;
/*    */     //   44	64	5	$i$f$firstOrNull	I
/*    */     //   35	77	3	subjectCaCerts	Ljava/util/Set;
/*    */     //   11	101	2	issuer	Ljavax/security/auth/x500/X500Principal;
/*    */     //   0	112	0	this	Lokhttp3/internal/tls/BasicTrustRootIndex;
/*    */     //   0	112	1	cert	Ljava/security/cert/X509Certificate;
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   82	96	96	java/lang/Exception
/*    */   }
/*    */   
/*    */   public boolean equals(@Nullable Object other) {
/*    */     return (other == this || (other instanceof BasicTrustRootIndex && Intrinsics.areEqual(((BasicTrustRootIndex)other).subjectToCaCerts, this.subjectToCaCerts)));
/*    */   }
/*    */   
/*    */   public int hashCode() {
/*    */     return this.subjectToCaCerts.hashCode();
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/tls/BasicTrustRootIndex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */