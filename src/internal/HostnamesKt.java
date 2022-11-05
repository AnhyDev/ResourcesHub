/*     */ package okhttp3.internal;
/*     */ 
/*     */ import java.net.IDN;
/*     */ import java.net.InetAddress;
/*     */ import java.util.Arrays;
/*     */ import java.util.Locale;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.text.StringsKt;
/*     */ import okio.Buffer;
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
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 2, d1 = {"\000&\n\000\n\002\020\013\n\000\n\002\020\016\n\000\n\002\020\b\n\002\b\002\n\002\020\022\n\002\b\002\n\002\030\002\n\002\b\004\0320\020\000\032\0020\0012\006\020\002\032\0020\0032\006\020\004\032\0020\0052\006\020\006\032\0020\0052\006\020\007\032\0020\b2\006\020\t\032\0020\005H\002\032\"\020\n\032\004\030\0010\0132\006\020\002\032\0020\0032\006\020\004\032\0020\0052\006\020\006\032\0020\005H\002\032\020\020\f\032\0020\0032\006\020\007\032\0020\bH\002\032\f\020\r\032\0020\001*\0020\003H\002\032\f\020\016\032\004\030\0010\003*\0020\003¨\006\017"}, d2 = {"decodeIpv4Suffix", "", "input", "", "pos", "", "limit", "address", "", "addressOffset", "decodeIpv6", "Ljava/net/InetAddress;", "inet6AddressToAscii", "containsInvalidHostnameAsciiCodes", "toCanonicalHost", "okhttp"})
/*     */ public final class HostnamesKt
/*     */ {
/*     */   @Nullable
/*     */   public static final String toCanonicalHost(@NotNull String $this$toCanonicalHost) {
/*  33 */     Intrinsics.checkNotNullParameter($this$toCanonicalHost, "$this$toCanonicalHost"); String host = $this$toCanonicalHost;
/*     */ 
/*     */     
/*  36 */     if (StringsKt.contains$default(host, ":", false, 2, null)) {
/*     */       
/*  38 */       if (((StringsKt.startsWith$default(host, "[", false, 2, null) && StringsKt.endsWith$default(host, "]", false, 2, null)) ? 
/*  39 */         decodeIpv6(host, 1, host.length() - 1) : 
/*     */         
/*  41 */         decodeIpv6(host, 0, host.length())) != null) { Object object = (StringsKt.startsWith$default(host, "[", false, 2, null) && StringsKt.endsWith$default(host, "]", false, 2, null)) ? decodeIpv6(host, 1, host.length() - 1) : decodeIpv6(host, 0, host.length());
/*     */         
/*  43 */         byte[] address = object.getAddress();
/*  44 */         if (address.length == 16) { Intrinsics.checkNotNullExpressionValue(address, "address"); return inet6AddressToAscii(address); }
/*  45 */          if (address.length == 4) return object.getHostAddress(); 
/*  46 */         throw new AssertionError("Invalid IPv6 address: '" + host + '\''); }
/*     */        (StringsKt.startsWith$default(host, "[", false, 2, null) && StringsKt.endsWith$default(host, "]", false, 2, null)) ? decodeIpv6(host, 1, host.length() - 1) : decodeIpv6(host, 0, host.length()); return null;
/*     */     } 
/*     */     try {
/*  50 */       Intrinsics.checkNotNullExpressionValue(IDN.toASCII(host), "IDN.toASCII(host)"); String str1 = IDN.toASCII(host); Intrinsics.checkNotNullExpressionValue(Locale.US, "Locale.US"); Locale locale = Locale.US; boolean bool2 = false; if (str1 == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str1.toLowerCase(locale), "(this as java.lang.String).toLowerCase(locale)"); String result = str1.toLowerCase(locale);
/*  51 */       CharSequence charSequence = result; boolean bool1 = false; if ((charSequence.length() == 0)) return null;
/*     */ 
/*     */       
/*  54 */       return containsInvalidHostnameAsciiCodes(result) ? 
/*  55 */         null : 
/*     */         
/*  57 */         result;
/*     */     }
/*  59 */     catch (IllegalArgumentException _) {
/*  60 */       return null;
/*     */     } 
/*     */   } private static final boolean containsInvalidHostnameAsciiCodes(String $this$containsInvalidHostnameAsciiCodes) {
/*     */     byte b;
/*     */     int i;
/*  65 */     for (b = 0, i = $this$containsInvalidHostnameAsciiCodes.length(); b < i; b++) {
/*  66 */       char c = $this$containsInvalidHostnameAsciiCodes.charAt(b);
/*     */ 
/*     */ 
/*     */       
/*  70 */       if (Intrinsics.compare(c, 31) <= 0 || Intrinsics.compare(c, 127) >= 0) {
/*  71 */         return true;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  76 */       if (StringsKt.indexOf$default(" #%/:?@[\\]", c, 0, false, 6, null) != -1) {
/*  77 */         return true;
/*     */       }
/*     */     } 
/*  80 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static final InetAddress decodeIpv6(String input, int pos, int limit) {
/*  85 */     byte[] address = new byte[16];
/*  86 */     int b = 0;
/*  87 */     int compress = -1;
/*  88 */     int groupOffset = -1;
/*     */     
/*  90 */     int i = pos;
/*  91 */     while (i < limit) {
/*  92 */       if (b == address.length) return null;
/*     */ 
/*     */       
/*  95 */       if (i + 2 <= limit && StringsKt.startsWith$default(input, "::", i, false, 4, null))
/*     */       
/*  97 */       { if (compress != -1) return null; 
/*  98 */         i += 2;
/*  99 */         b += 2;
/* 100 */         compress = b;
/* 101 */         if (i == limit)
/* 102 */           break;  } else if (b != 0)
/*     */       
/* 104 */       { if (StringsKt.startsWith$default(input, ":", i, false, 4, null))
/* 105 */         { i++; }
/* 106 */         else { if (StringsKt.startsWith$default(input, ".", i, false, 4, null)) {
/*     */             
/* 108 */             if (!decodeIpv4Suffix(input, groupOffset, limit, address, b - 2)) return null; 
/* 109 */             b += 2;
/*     */             break;
/*     */           } 
/* 112 */           return null; }
/*     */          }
/*     */ 
/*     */ 
/*     */       
/* 117 */       int value = 0;
/* 118 */       groupOffset = i;
/* 119 */       while (i < limit) {
/* 120 */         int hexDigit = Util.parseHexDigit(input.charAt(i));
/* 121 */         if (hexDigit == -1)
/* 122 */           break;  value = (value << 4) + hexDigit;
/* 123 */         i++;
/*     */       } 
/* 125 */       int groupLength = i - groupOffset;
/* 126 */       if (groupLength == 0 || groupLength > 4) return null;
/*     */ 
/*     */       
/* 129 */       address[b++] = (byte)(value >>> 8 & 0xFF);
/* 130 */       address[b++] = (byte)(value & 0xFF);
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
/* 142 */     if (b != address.length) {
/* 143 */       if (compress == -1) return null; 
/* 144 */       System.arraycopy(address, compress, address, address.length - b - compress, b - compress);
/* 145 */       Arrays.fill(address, compress, compress + address.length - b, (byte)0);
/*     */     } 
/*     */     
/* 148 */     return InetAddress.getByAddress(address);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final boolean decodeIpv4Suffix(String input, int pos, int limit, byte[] address, int addressOffset) {
/* 159 */     int b = addressOffset;
/*     */     
/* 161 */     int i = pos;
/* 162 */     while (i < limit) {
/* 163 */       if (b == address.length) return false;
/*     */ 
/*     */       
/* 166 */       if (b != addressOffset) {
/* 167 */         if (input.charAt(i) != '.') return false; 
/* 168 */         i++;
/*     */       } 
/*     */ 
/*     */       
/* 172 */       int value = 0;
/* 173 */       int groupOffset = i;
/* 174 */       while (i < limit) {
/* 175 */         char c = input.charAt(i);
/* 176 */         if (Intrinsics.compare(c, 48) < 0 || Intrinsics.compare(c, 57) > 0)
/* 177 */           break;  if (value == 0 && groupOffset != i) return false; 
/* 178 */         value = value * 10 + c - 48;
/* 179 */         if (value > 255) return false; 
/* 180 */         i++;
/*     */       } 
/* 182 */       int groupLength = i - groupOffset;
/* 183 */       if (groupLength == 0) return false;
/*     */ 
/*     */       
/* 186 */       address[b++] = (byte)value;
/*     */     } 
/*     */ 
/*     */     
/* 190 */     return (b == addressOffset + 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String inet6AddressToAscii(byte[] address) {
/* 198 */     int longestRunOffset = -1;
/* 199 */     int longestRunLength = 0;
/* 200 */     boolean bool1 = false, bool2 = false; int $i$a$-run-HostnamesKt$inet6AddressToAscii$1 = 0;
/* 201 */     int j = 0;
/* 202 */     while (j < address.length) {
/* 203 */       int currentRunOffset = j;
/* 204 */       while (j < 16 && address[j] == 0 && address[j + 1] == 0) {
/* 205 */         j += 2;
/*     */       }
/* 207 */       int currentRunLength = j - currentRunOffset;
/* 208 */       if (currentRunLength > longestRunLength && currentRunLength >= 4) {
/* 209 */         longestRunOffset = currentRunOffset;
/* 210 */         longestRunLength = currentRunLength;
/*     */       } 
/* 212 */       j += 2;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 217 */     Buffer result = new Buffer();
/* 218 */     int i = 0;
/* 219 */     while (i < address.length) {
/* 220 */       if (i == longestRunOffset) {
/* 221 */         result.writeByte(58);
/* 222 */         i += longestRunLength;
/* 223 */         if (i == 16) result.writeByte(58);  continue;
/*     */       } 
/* 225 */       if (i > 0) result.writeByte(58); 
/* 226 */       int group = Util.and(address[i], 255) << 8 | Util.and(address[i + 1], 255);
/* 227 */       result.writeHexadecimalUnsignedLong(group);
/* 228 */       i += 2;
/*     */     } 
/*     */     
/* 231 */     return result.readUtf8();
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/HostnamesKt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */