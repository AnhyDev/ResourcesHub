/*     */ package okhttp3.internal.publicsuffix;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InterruptedIOException;
/*     */ import java.net.IDN;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CountDownLatch;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.Unit;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.io.CloseableKt;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.sequences.SequencesKt;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.platform.Platform;
/*     */ import okio.BufferedSource;
/*     */ import okio.GzipSource;
/*     */ import okio.Okio;
/*     */ import okio.Source;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0004\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\022\n\002\b\002\n\002\030\002\n\000\n\002\020 \n\002\020\016\n\002\b\004\n\002\020\002\n\002\b\005\030\000 \0252\0020\001:\001\025B\005¢\006\002\020\002J\034\020\n\032\b\022\004\022\0020\f0\0132\f\020\r\032\b\022\004\022\0020\f0\013H\002J\020\020\016\032\004\030\0010\f2\006\020\017\032\0020\fJ\b\020\020\032\0020\021H\002J\b\020\022\032\0020\021H\002J\026\020\023\032\0020\0212\006\020\007\032\0020\0062\006\020\005\032\0020\006J\026\020\024\032\b\022\004\022\0020\f0\0132\006\020\017\032\0020\fH\002R\016\020\003\032\0020\004X\004¢\006\002\n\000R\016\020\005\032\0020\006X.¢\006\002\n\000R\016\020\007\032\0020\006X.¢\006\002\n\000R\016\020\b\032\0020\tX\004¢\006\002\n\000¨\006\026"}, d2 = {"Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", "", "()V", "listRead", "Ljava/util/concurrent/atomic/AtomicBoolean;", "publicSuffixExceptionListBytes", "", "publicSuffixListBytes", "readCompleteLatch", "Ljava/util/concurrent/CountDownLatch;", "findMatchingRule", "", "", "domainLabels", "getEffectiveTldPlusOne", "domain", "readTheList", "", "readTheListUninterruptibly", "setListBytes", "splitDomain", "Companion", "okhttp"})
/*     */ public final class PublicSuffixDatabase
/*     */ {
/*  38 */   private final AtomicBoolean listRead = new AtomicBoolean(false);
/*     */ 
/*     */   
/*  41 */   private final CountDownLatch readCompleteLatch = new CountDownLatch(1);
/*     */ 
/*     */   
/*     */   private byte[] publicSuffixListBytes;
/*     */ 
/*     */   
/*     */   private byte[] publicSuffixExceptionListBytes;
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";
/*     */ 
/*     */   
/*     */   private static final byte[] WILDCARD_LABEL;
/*     */ 
/*     */   
/*     */   private static final List<String> PREVAILING_RULE;
/*     */ 
/*     */   
/*     */   private static final char EXCEPTION_MARKER = '!';
/*     */ 
/*     */   
/*     */   private static final PublicSuffixDatabase instance;
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public final String getEffectiveTldPlusOne(@NotNull String domain) {
/*  69 */     Intrinsics.checkNotNullParameter(domain, "domain"); String unicodeDomain = IDN.toUnicode(domain);
/*  70 */     Intrinsics.checkNotNullExpressionValue(unicodeDomain, "unicodeDomain"); List<String> domainLabels = splitDomain(unicodeDomain);
/*     */     
/*  72 */     List<String> rule = findMatchingRule(domainLabels);
/*  73 */     if (domainLabels.size() == rule.size() && ((String)rule.get(0)).charAt(0) != '!') {
/*  74 */       return null;
/*     */     }
/*     */     
/*  77 */     int firstLabelOffset = (((String)rule.get(0)).charAt(0) == '!') ? (
/*     */       
/*  79 */       domainLabels.size() - rule.size()) : (
/*     */ 
/*     */       
/*  82 */       domainLabels.size() - rule.size() + 1);
/*     */ 
/*     */     
/*  85 */     return SequencesKt.joinToString$default(SequencesKt.drop(CollectionsKt.asSequence(splitDomain(domain)), firstLabelOffset), ".", null, null, 0, null, null, 62, null);
/*     */   }
/*     */   
/*     */   private final List<String> splitDomain(String domain) {
/*  89 */     List<String> domainLabels = StringsKt.split$default(domain, new char[] { '.' }, false, 0, 6, null);
/*     */     
/*  91 */     if (Intrinsics.areEqual(CollectionsKt.last(domainLabels), ""))
/*     */     {
/*  93 */       return CollectionsKt.dropLast(domainLabels, 1);
/*     */     }
/*     */     
/*  96 */     return domainLabels;
/*     */   }
/*     */   
/*     */   private final List<String> findMatchingRule(List<String> domainLabels) {
/* 100 */     if (!this.listRead.get() && this.listRead.compareAndSet(false, true)) {
/* 101 */       readTheListUninterruptibly();
/*     */     } else {
/*     */       try {
/* 104 */         this.readCompleteLatch.await();
/* 105 */       } catch (InterruptedException _) {
/* 106 */         Thread.currentThread().interrupt();
/*     */       } 
/*     */     } 
/*     */     
/* 110 */     boolean bool1 = (this.publicSuffixListBytes != null) ? true : false; int i = 0; boolean bool2 = false; if (!bool1) { int $i$a$-check-PublicSuffixDatabase$findMatchingRule$2 = 0; String str = 
/* 111 */         "Unable to load publicsuffixes.gz resource from the classpath."; throw (Throwable)new IllegalStateException(str.toString()); }
/*     */     
/*     */     byte[][] arrayOfByte1;
/*     */     int j;
/* 115 */     for (i = domainLabels.size(), arrayOfByte1 = new byte[i][], j = 0; j < i; ) { int k = j, m = j; byte[][] arrayOfByte = arrayOfByte1; int $i$a$-<init>-PublicSuffixDatabase$findMatchingRule$domainLabelsUtf8Bytes$1 = 0; String str = domainLabels.get(k); Intrinsics.checkNotNullExpressionValue(StandardCharsets.UTF_8, "UTF_8"); Charset charset = StandardCharsets.UTF_8; boolean bool = false; if (str == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str.getBytes(charset), "(this as java.lang.String).getBytes(charset)"); byte[] arrayOfByte2 = str.getBytes(charset); arrayOfByte[m] = arrayOfByte2; j++; }  byte[][] domainLabelsUtf8Bytes = arrayOfByte1;
/*     */ 
/*     */ 
/*     */     
/* 119 */     String exactMatch = (String)null;
/* 120 */     for (byte b = 0; b < j; b++) {
/* 121 */       if (this.publicSuffixListBytes == null) Intrinsics.throwUninitializedPropertyAccessException("publicSuffixListBytes");  String rule = Companion.binarySearch(this.publicSuffixListBytes, domainLabelsUtf8Bytes, b);
/* 122 */       if (rule != null) {
/* 123 */         exactMatch = rule;
/*     */ 
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 133 */     String str1 = (String)null;
/* 134 */     if (((Object[])domainLabelsUtf8Bytes).length > 1) {
/* 135 */       byte[][] labelsWithWildcard = (byte[][])((Object[])domainLabelsUtf8Bytes).clone();
/* 136 */       for (int k = 0, m = ((Object[])labelsWithWildcard).length - 1; k < m; k++) {
/* 137 */         labelsWithWildcard[k] = WILDCARD_LABEL;
/* 138 */         if (this.publicSuffixListBytes == null) Intrinsics.throwUninitializedPropertyAccessException("publicSuffixListBytes");  String rule = Companion.binarySearch(this.publicSuffixListBytes, labelsWithWildcard, k);
/* 139 */         if (rule != null) {
/* 140 */           str1 = rule;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 147 */     String exception = (String)null;
/* 148 */     if (str1 != null) {
/* 149 */       for (int labelIndex = 0, k = ((Object[])domainLabelsUtf8Bytes).length - 1; labelIndex < k; labelIndex++) {
/* 150 */         if (this.publicSuffixExceptionListBytes == null) Intrinsics.throwUninitializedPropertyAccessException("publicSuffixExceptionListBytes");  String rule = Companion.binarySearch(this.publicSuffixExceptionListBytes, 
/* 151 */             domainLabelsUtf8Bytes, labelIndex);
/* 152 */         if (rule != null) {
/* 153 */           exception = rule;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 159 */     if (exception != null) {
/*     */       
/* 161 */       exception = '!' + exception;
/* 162 */       return StringsKt.split$default(exception, new char[] { '.' }, false, 0, 6, null);
/* 163 */     }  if (exactMatch == null && str1 == null) {
/* 164 */       return PREVAILING_RULE;
/*     */     }
/*     */     
/* 167 */     if (exactMatch == null || StringsKt.split$default(exactMatch, new char[] { '.' }, false, 0, 6, null) == null) { StringsKt.split$default(exactMatch, new char[] { '.' }, false, 0, 6, null); boolean bool = false; }  List<String> exactRuleLabels = CollectionsKt.emptyList();
/* 168 */     if (str1 == null || StringsKt.split$default(str1, new char[] { '.' }, false, 0, 6, null) == null) { StringsKt.split$default(str1, new char[] { '.' }, false, 0, 6, null); boolean bool = false; }  List<String> wildcardRuleLabels = CollectionsKt.emptyList();
/*     */     
/* 170 */     return (exactRuleLabels.size() > wildcardRuleLabels.size()) ? 
/* 171 */       exactRuleLabels : 
/*     */       
/* 173 */       wildcardRuleLabels;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void readTheListUninterruptibly() {
/*     */     Exception exception;
/* 183 */     boolean interrupted = false;
/*     */     
/*     */     try { while (true)
/*     */       { 
/* 187 */         try { readTheList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 198 */           if (interrupted)
/* 199 */             Thread.currentThread().interrupt();  return; } catch (InterruptedIOException _) { Thread.interrupted(); interrupted = true; } catch (IOException e) { Platform.Companion.get().log("Failed to read public suffix list", 5, exception); break; }  }  if (interrupted) Thread.currentThread().interrupt();  return; } finally {} if (interrupted) Thread.currentThread().interrupt();  throw exception;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void readTheList() throws IOException {
/* 206 */     Object publicSuffixListBytes = null;
/* 207 */     Object publicSuffixExceptionListBytes = null;
/*     */ 
/*     */     
/* 210 */     if (PublicSuffixDatabase.class.getResourceAsStream("publicsuffixes.gz") != null) { InputStream resource = PublicSuffixDatabase.class.getResourceAsStream("publicsuffixes.gz");
/*     */       
/* 212 */       Closeable closeable = (Closeable)Okio.buffer((Source)new GzipSource(Okio.source(resource))); boolean bool1 = false, bool2 = false; Throwable throwable = (Throwable)null; try { BufferedSource bufferedSource = (BufferedSource)closeable; int $i$a$-use-PublicSuffixDatabase$readTheList$1 = 0;
/* 213 */         int totalBytes = bufferedSource.readInt();
/* 214 */         publicSuffixListBytes = bufferedSource.readByteArray(totalBytes);
/*     */         
/* 216 */         int totalExceptionBytes = bufferedSource.readInt();
/* 217 */         publicSuffixExceptionListBytes = bufferedSource.readByteArray(totalExceptionBytes);
/* 218 */         Unit unit = Unit.INSTANCE; } catch (Throwable throwable1) { throwable = throwable1 = null; throw throwable1; }
/*     */       finally { CloseableKt.closeFinally(closeable, throwable); }
/* 220 */        PublicSuffixDatabase publicSuffixDatabase = this; bool1 = false; synchronized (false) { int $i$a$-synchronized-PublicSuffixDatabase$readTheList$2 = 0;
/* 221 */         Intrinsics.checkNotNull(publicSuffixListBytes); this.publicSuffixListBytes = (byte[])publicSuffixListBytes;
/* 222 */         Intrinsics.checkNotNull(publicSuffixExceptionListBytes); this.publicSuffixExceptionListBytes = (byte[])publicSuffixExceptionListBytes;
/* 223 */         Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/publicsuffix/PublicSuffixDatabase}, name=null} */ }
/*     */       
/* 225 */       this.readCompleteLatch.countDown();
/*     */       return; }
/*     */     
/*     */     PublicSuffixDatabase.class.getResourceAsStream("publicsuffixes.gz");
/*     */   }
/*     */ 
/*     */   
/*     */   public final void setListBytes(@NotNull byte[] publicSuffixListBytes, @NotNull byte[] publicSuffixExceptionListBytes) {
/* 233 */     Intrinsics.checkNotNullParameter(publicSuffixListBytes, "publicSuffixListBytes"); Intrinsics.checkNotNullParameter(publicSuffixExceptionListBytes, "publicSuffixExceptionListBytes"); this.publicSuffixListBytes = publicSuffixListBytes;
/* 234 */     this.publicSuffixExceptionListBytes = publicSuffixExceptionListBytes;
/* 235 */     this.listRead.set(true);
/* 236 */     this.readCompleteLatch.countDown();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 242 */   public static final Companion Companion = new Companion(null); static { WILDCARD_LABEL = new byte[] { (byte)42 };
/* 243 */     PREVAILING_RULE = CollectionsKt.listOf("*");
/*     */ 
/*     */ 
/*     */     
/* 247 */     instance = new PublicSuffixDatabase(); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000:\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\f\n\000\n\002\020 \n\002\020\016\n\002\b\002\n\002\020\022\n\000\n\002\030\002\n\002\b\003\n\002\020\021\n\000\n\002\020\b\n\002\b\002\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\006\020\r\032\0020\fJ)\020\016\032\004\030\0010\007*\0020\n2\f\020\017\032\b\022\004\022\0020\n0\0202\006\020\021\032\0020\022H\002¢\006\002\020\023R\016\020\003\032\0020\004XT¢\006\002\n\000R\024\020\005\032\b\022\004\022\0020\0070\006X\004¢\006\002\n\000R\016\020\b\032\0020\007XT¢\006\002\n\000R\016\020\t\032\0020\nX\004¢\006\002\n\000R\016\020\013\032\0020\fX\004¢\006\002\n\000¨\006\024"}, d2 = {"Lokhttp3/internal/publicsuffix/PublicSuffixDatabase$Companion;", "", "()V", "EXCEPTION_MARKER", "", "PREVAILING_RULE", "", "", "PUBLIC_SUFFIX_RESOURCE", "WILDCARD_LABEL", "", "instance", "Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", "get", "binarySearch", "labels", "", "labelIndex", "", "([B[[BI)Ljava/lang/String;", "okhttp"})
/*     */   public static final class Companion { private Companion() {} @NotNull
/*     */     public final PublicSuffixDatabase get() {
/* 250 */       return PublicSuffixDatabase.instance;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final String binarySearch(byte[] $this$binarySearch, byte[][] labels, int labelIndex) {
/* 257 */       int low = 0;
/* 258 */       int high = $this$binarySearch.length;
/* 259 */       String match = (String)null;
/* 260 */       while (low < high) {
/* 261 */         int mid = (low + high) / 2;
/*     */ 
/*     */         
/* 264 */         while (mid > -1 && $this$binarySearch[mid] != (byte)10) {
/* 265 */           mid--;
/*     */         }
/* 267 */         mid++;
/*     */ 
/*     */         
/* 270 */         int end = 1;
/* 271 */         while ($this$binarySearch[mid + end] != (byte)10) {
/* 272 */           end++;
/*     */         }
/* 274 */         int publicSuffixLength = mid + end - mid;
/*     */ 
/*     */ 
/*     */         
/* 278 */         int compareResult = 0;
/* 279 */         int currentLabelIndex = labelIndex;
/* 280 */         int currentLabelByteIndex = 0;
/* 281 */         int publicSuffixByteIndex = 0;
/*     */         
/* 283 */         boolean expectDot = false;
/*     */         while (true) {
/* 285 */           int byte0 = 0;
/* 286 */           if (expectDot) {
/* 287 */             byte0 = 46;
/* 288 */             expectDot = false;
/*     */           } else {
/* 290 */             byte0 = Util.and(labels[currentLabelIndex][currentLabelByteIndex], 255);
/*     */           } 
/*     */           
/* 293 */           int byte1 = Util.and($this$binarySearch[mid + publicSuffixByteIndex], 255);
/*     */           
/* 295 */           compareResult = byte0 - byte1;
/* 296 */           if (compareResult != 0)
/*     */             break; 
/* 298 */           publicSuffixByteIndex++;
/* 299 */           currentLabelByteIndex++;
/* 300 */           if (publicSuffixByteIndex == publicSuffixLength)
/*     */             break; 
/* 302 */           if ((labels[currentLabelIndex]).length == currentLabelByteIndex) {
/*     */ 
/*     */             
/* 305 */             if (currentLabelIndex == ((Object[])labels).length - 1) {
/*     */               break;
/*     */             }
/* 308 */             currentLabelIndex++;
/* 309 */             currentLabelByteIndex = -1;
/* 310 */             expectDot = true;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 315 */         if (compareResult < 0) {
/* 316 */           high = mid - 1; continue;
/* 317 */         }  if (compareResult > 0) {
/* 318 */           low = mid + end + 1;
/*     */           continue;
/*     */         } 
/* 321 */         int publicSuffixBytesLeft = publicSuffixLength - publicSuffixByteIndex;
/* 322 */         int labelBytesLeft = (labels[currentLabelIndex]).length - currentLabelByteIndex; int j;
/* 323 */         for (int i = currentLabelIndex + 1; i < j; i++) {
/* 324 */           labelBytesLeft += (labels[i]).length;
/*     */         }
/*     */         
/* 327 */         if (labelBytesLeft < publicSuffixBytesLeft) {
/* 328 */           high = mid - 1; continue;
/* 329 */         }  if (labelBytesLeft > publicSuffixBytesLeft) {
/* 330 */           low = mid + end + 1;
/*     */           continue;
/*     */         } 
/* 333 */         Intrinsics.checkNotNullExpressionValue(StandardCharsets.UTF_8, "UTF_8"); Charset charset = StandardCharsets.UTF_8; j = 0; match = new String($this$binarySearch, mid, publicSuffixLength, charset);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 338 */       return match;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/publicsuffix/PublicSuffixDatabase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */