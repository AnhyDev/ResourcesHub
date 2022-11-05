/*     */ package okhttp3.internal.http2;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.collections.ArraysKt;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.jvm.JvmField;
/*     */ import kotlin.jvm.JvmOverloads;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.internal.Util;
/*     */ import okio.Buffer;
/*     */ import okio.BufferedSink;
/*     */ import okio.BufferedSource;
/*     */ import okio.ByteString;
/*     */ import okio.Okio;
/*     */ import okio.Source;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000(\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020$\n\002\030\002\n\002\020\b\n\002\b\t\n\002\020\021\n\002\030\002\n\002\b\t\bÆ\002\030\0002\0020\001:\002\030\031B\007\b\002¢\006\002\020\002J\016\020\025\032\0020\0052\006\020\026\032\0020\005J\024\020\027\032\016\022\004\022\0020\005\022\004\022\0020\0060\004H\002R\035\020\003\032\016\022\004\022\0020\005\022\004\022\0020\0060\004¢\006\b\n\000\032\004\b\007\020\bR\016\020\t\032\0020\006XT¢\006\002\n\000R\016\020\n\032\0020\006XT¢\006\002\n\000R\016\020\013\032\0020\006XT¢\006\002\n\000R\016\020\f\032\0020\006XT¢\006\002\n\000R\016\020\r\032\0020\006XT¢\006\002\n\000R\016\020\016\032\0020\006XT¢\006\002\n\000R\031\020\017\032\b\022\004\022\0020\0210\020¢\006\n\n\002\020\024\032\004\b\022\020\023¨\006\032"}, d2 = {"Lokhttp3/internal/http2/Hpack;", "", "()V", "NAME_TO_FIRST_INDEX", "", "Lokio/ByteString;", "", "getNAME_TO_FIRST_INDEX", "()Ljava/util/Map;", "PREFIX_4_BITS", "PREFIX_5_BITS", "PREFIX_6_BITS", "PREFIX_7_BITS", "SETTINGS_HEADER_TABLE_SIZE", "SETTINGS_HEADER_TABLE_SIZE_LIMIT", "STATIC_HEADER_TABLE", "", "Lokhttp3/internal/http2/Header;", "getSTATIC_HEADER_TABLE", "()[Lokhttp3/internal/http2/Header;", "[Lokhttp3/internal/http2/Header;", "checkLowercase", "name", "nameToFirstIndex", "Reader", "Writer", "okhttp"})
/*     */ public final class Hpack
/*     */ {
/*     */   private static final int PREFIX_4_BITS = 15;
/*     */   private static final int PREFIX_5_BITS = 31;
/*     */   private static final int PREFIX_6_BITS = 63;
/*     */   private static final int PREFIX_7_BITS = 127;
/*     */   private static final int SETTINGS_HEADER_TABLE_SIZE = 4096;
/*     */   private static final int SETTINGS_HEADER_TABLE_SIZE_LIMIT = 16384;
/*     */   
/*     */   static {
/*  44 */     Hpack hpack = new Hpack();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*  58 */   private static final Header[] STATIC_HEADER_TABLE = new Header[] { 
/*  59 */       new Header(Header.TARGET_AUTHORITY, ""), 
/*  60 */       new Header(Header.TARGET_METHOD, "GET"), 
/*  61 */       new Header(Header.TARGET_METHOD, "POST"), 
/*  62 */       new Header(Header.TARGET_PATH, "/"), 
/*  63 */       new Header(Header.TARGET_PATH, "/index.html"), 
/*  64 */       new Header(Header.TARGET_SCHEME, "http"), 
/*  65 */       new Header(Header.TARGET_SCHEME, "https"), 
/*  66 */       new Header(Header.RESPONSE_STATUS, "200"), 
/*  67 */       new Header(Header.RESPONSE_STATUS, "204"), 
/*  68 */       new Header(Header.RESPONSE_STATUS, "206"), 
/*  69 */       new Header(Header.RESPONSE_STATUS, "304"), 
/*  70 */       new Header(Header.RESPONSE_STATUS, "400"), 
/*  71 */       new Header(Header.RESPONSE_STATUS, "404"), 
/*  72 */       new Header(Header.RESPONSE_STATUS, "500"), 
/*  73 */       new Header("accept-charset", ""), 
/*  74 */       new Header("accept-encoding", "gzip, deflate"), 
/*  75 */       new Header("accept-language", ""), 
/*  76 */       new Header("accept-ranges", ""), 
/*  77 */       new Header("accept", ""), 
/*  78 */       new Header("access-control-allow-origin", ""), 
/*  79 */       new Header("age", ""), 
/*  80 */       new Header("allow", ""), 
/*  81 */       new Header("authorization", ""), 
/*  82 */       new Header("cache-control", ""), 
/*  83 */       new Header("content-disposition", ""), 
/*  84 */       new Header("content-encoding", ""), 
/*  85 */       new Header("content-language", ""), 
/*  86 */       new Header("content-length", ""), 
/*  87 */       new Header("content-location", ""), 
/*  88 */       new Header("content-range", ""), 
/*  89 */       new Header("content-type", ""), 
/*  90 */       new Header("cookie", ""), 
/*  91 */       new Header("date", ""), 
/*  92 */       new Header("etag", ""), 
/*  93 */       new Header("expect", ""), 
/*  94 */       new Header("expires", ""), 
/*  95 */       new Header("from", ""), 
/*  96 */       new Header("host", ""), 
/*  97 */       new Header("if-match", ""), 
/*  98 */       new Header("if-modified-since", ""), 
/*  99 */       new Header("if-none-match", ""), 
/* 100 */       new Header("if-range", ""), 
/* 101 */       new Header("if-unmodified-since", ""), 
/* 102 */       new Header("last-modified", ""), 
/* 103 */       new Header("link", ""), 
/* 104 */       new Header("location", ""), 
/* 105 */       new Header("max-forwards", ""), 
/* 106 */       new Header("proxy-authenticate", ""), 
/* 107 */       new Header("proxy-authorization", ""), 
/* 108 */       new Header("range", ""), 
/* 109 */       new Header("referer", ""), 
/* 110 */       new Header("refresh", ""), 
/* 111 */       new Header("retry-after", ""), 
/* 112 */       new Header("server", ""), 
/* 113 */       new Header("set-cookie", ""), 
/* 114 */       new Header("strict-transport-security", ""), 
/* 115 */       new Header("transfer-encoding", ""), 
/* 116 */       new Header("user-agent", ""), 
/* 117 */       new Header("vary", ""), 
/* 118 */       new Header("via", ""), 
/* 119 */       new Header("www-authenticate", "") }; @NotNull private static final Map<ByteString, Integer> NAME_TO_FIRST_INDEX; public static final Hpack INSTANCE; @NotNull
/*     */   public final Header[] getSTATIC_HEADER_TABLE() { return STATIC_HEADER_TABLE; }
/*     */   @NotNull
/* 122 */   public final Map<ByteString, Integer> getNAME_TO_FIRST_INDEX() { return NAME_TO_FIRST_INDEX; } static { NAME_TO_FIRST_INDEX = hpack.nameToFirstIndex(); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000N\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\020\b\n\002\b\003\n\002\020\021\n\002\030\002\n\002\b\004\n\002\020!\n\000\n\002\030\002\n\000\n\002\020\002\n\002\b\006\n\002\020 \n\000\n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\r\030\0002\0020\001B!\b\007\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\b\b\002\020\006\032\0020\005¢\006\002\020\007J\b\020\022\032\0020\023H\002J\b\020\024\032\0020\023H\002J\020\020\025\032\0020\0052\006\020\026\032\0020\005H\002J\020\020\027\032\0020\0052\006\020\030\032\0020\005H\002J\f\020\031\032\b\022\004\022\0020\n0\032J\020\020\033\032\0020\0342\006\020\026\032\0020\005H\002J\030\020\035\032\0020\0232\006\020\026\032\0020\0052\006\020\036\032\0020\nH\002J\020\020\037\032\0020 2\006\020\026\032\0020\005H\002J\006\020\006\032\0020\005J\b\020!\032\0020\005H\002J\006\020\"\032\0020\034J\006\020#\032\0020\023J\020\020$\032\0020\0232\006\020\026\032\0020\005H\002J\026\020%\032\0020\0052\006\020&\032\0020\0052\006\020'\032\0020\005J\020\020(\032\0020\0232\006\020)\032\0020\005H\002J\b\020*\032\0020\023H\002J\020\020+\032\0020\0232\006\020\026\032\0020\005H\002J\b\020,\032\0020\023H\002R\034\020\b\032\n\022\006\022\004\030\0010\n0\t8\006@\006X\016¢\006\004\n\002\020\013R\022\020\f\032\0020\0058\006@\006X\016¢\006\002\n\000R\022\020\r\032\0020\0058\006@\006X\016¢\006\002\n\000R\024\020\016\032\b\022\004\022\0020\n0\017X\004¢\006\002\n\000R\016\020\004\032\0020\005X\004¢\006\002\n\000R\016\020\006\032\0020\005X\016¢\006\002\n\000R\016\020\020\032\0020\005X\016¢\006\002\n\000R\016\020\002\032\0020\021X\004¢\006\002\n\000¨\006-"}, d2 = {"Lokhttp3/internal/http2/Hpack$Reader;", "", "source", "Lokio/Source;", "headerTableSizeSetting", "", "maxDynamicTableByteCount", "(Lokio/Source;II)V", "dynamicTable", "", "Lokhttp3/internal/http2/Header;", "[Lokhttp3/internal/http2/Header;", "dynamicTableByteCount", "headerCount", "headerList", "", "nextHeaderIndex", "Lokio/BufferedSource;", "adjustDynamicTableByteCount", "", "clearDynamicTable", "dynamicTableIndex", "index", "evictToRecoverBytes", "bytesToRecover", "getAndResetHeaderList", "", "getName", "Lokio/ByteString;", "insertIntoDynamicTable", "entry", "isStaticHeader", "", "readByte", "readByteString", "readHeaders", "readIndexedHeader", "readInt", "firstByte", "prefixMask", "readLiteralHeaderWithIncrementalIndexingIndexedName", "nameIndex", "readLiteralHeaderWithIncrementalIndexingNewName", "readLiteralHeaderWithoutIndexingIndexedName", "readLiteralHeaderWithoutIndexingNewName", "okhttp"}) public static final class Reader { private final List<Header> headerList; private final BufferedSource source; @JvmField @NotNull public Header[] dynamicTable; private int nextHeaderIndex; @JvmField
/*     */     public int headerCount; @JvmField
/*     */     public int dynamicTableByteCount; private final int headerTableSizeSetting; private int maxDynamicTableByteCount; @JvmOverloads
/* 125 */     public Reader(@NotNull Source source, int headerTableSizeSetting, int maxDynamicTableByteCount) { this.headerTableSizeSetting = headerTableSizeSetting; this.maxDynamicTableByteCount = maxDynamicTableByteCount;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 130 */       boolean bool = false; this.headerList = new ArrayList<>();
/* 131 */       this.source = Okio.buffer(source);
/*     */ 
/*     */       
/* 134 */       this.dynamicTable = new Header[8];
/*     */       
/* 136 */       this.nextHeaderIndex = this.dynamicTable.length - 1; }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public final List<Header> getAndResetHeaderList() {
/* 141 */       List<Header> result = CollectionsKt.toList(this.headerList);
/* 142 */       this.headerList.clear();
/* 143 */       return result;
/*     */     }
/*     */     public final int maxDynamicTableByteCount() {
/* 146 */       return this.maxDynamicTableByteCount;
/*     */     }
/*     */     private final void adjustDynamicTableByteCount() {
/* 149 */       if (this.maxDynamicTableByteCount < this.dynamicTableByteCount) {
/* 150 */         if (this.maxDynamicTableByteCount == 0) {
/* 151 */           clearDynamicTable();
/*     */         } else {
/* 153 */           evictToRecoverBytes(this.dynamicTableByteCount - this.maxDynamicTableByteCount);
/*     */         } 
/*     */       }
/*     */     }
/*     */     
/*     */     private final void clearDynamicTable() {
/* 159 */       ArraysKt.fill$default((Object[])this.dynamicTable, null, 0, 0, 6, null);
/* 160 */       this.nextHeaderIndex = this.dynamicTable.length - 1;
/* 161 */       this.headerCount = 0;
/* 162 */       this.dynamicTableByteCount = 0;
/*     */     }
/*     */ 
/*     */     
/*     */     private final int evictToRecoverBytes(int bytesToRecover) {
/* 167 */       int i = bytesToRecover;
/* 168 */       int entriesToEvict = 0;
/* 169 */       if (i > 0) {
/*     */         
/* 171 */         int j = this.dynamicTable.length - 1;
/* 172 */         while (j >= this.nextHeaderIndex && i > 0) {
/* 173 */           Intrinsics.checkNotNull(this.dynamicTable[j]); Header toEvict = this.dynamicTable[j];
/* 174 */           i -= toEvict.hpackSize;
/* 175 */           this.dynamicTableByteCount -= toEvict.hpackSize; int k;
/* 176 */           this.headerCount = (k = this.headerCount) + -1;
/* 177 */           entriesToEvict++;
/* 178 */           j--;
/*     */         } 
/* 180 */         System.arraycopy(this.dynamicTable, this.nextHeaderIndex + 1, this.dynamicTable, 
/* 181 */             this.nextHeaderIndex + 1 + entriesToEvict, this.headerCount);
/* 182 */         this.nextHeaderIndex += entriesToEvict;
/*     */       } 
/* 184 */       return entriesToEvict;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public final void readHeaders() throws IOException {
/* 193 */       while (!this.source.exhausted()) {
/* 194 */         int b = Util.and(this.source.readByte(), 255);
/*     */         
/* 196 */         if (b == 128)
/*     */         {
/* 198 */           throw (Throwable)new IOException("index == 0");
/*     */         }
/* 200 */         if ((b & 0x80) == 128) {
/*     */           
/* 202 */           int i = readInt(b, 127);
/* 203 */           readIndexedHeader(i - 1); continue;
/*     */         } 
/* 205 */         if (b == 64) {
/*     */           
/* 207 */           readLiteralHeaderWithIncrementalIndexingNewName(); continue;
/*     */         } 
/* 209 */         if ((b & 0x40) == 64) {
/*     */           
/* 211 */           int i = readInt(b, 63);
/* 212 */           readLiteralHeaderWithIncrementalIndexingIndexedName(i - 1); continue;
/*     */         } 
/* 214 */         if ((b & 0x20) == 32) {
/*     */           
/* 216 */           this.maxDynamicTableByteCount = readInt(b, 31);
/* 217 */           if (this.maxDynamicTableByteCount < 0 || this.maxDynamicTableByteCount > this.headerTableSizeSetting) {
/* 218 */             throw (Throwable)new IOException("Invalid dynamic table size update " + this.maxDynamicTableByteCount);
/*     */           }
/* 220 */           adjustDynamicTableByteCount(); continue;
/*     */         } 
/* 222 */         if (b == 16 || b == 0) {
/*     */           
/* 224 */           readLiteralHeaderWithoutIndexingNewName();
/*     */           
/*     */           continue;
/*     */         } 
/* 228 */         int index = readInt(b, 15);
/* 229 */         readLiteralHeaderWithoutIndexingIndexedName(index - 1);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final void readIndexedHeader(int index) throws IOException {
/* 237 */       if (isStaticHeader(index)) {
/* 238 */         Header staticEntry = Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[index];
/* 239 */         this.headerList.add(staticEntry);
/*     */       } else {
/* 241 */         int dynamicTableIndex = dynamicTableIndex(index - (Hpack.INSTANCE.getSTATIC_HEADER_TABLE()).length);
/* 242 */         if (dynamicTableIndex < 0 || dynamicTableIndex >= this.dynamicTable.length) {
/* 243 */           throw (Throwable)new IOException("Header index too large " + (index + 1));
/*     */         }
/* 245 */         List<Header> list = this.headerList; Intrinsics.checkNotNull(this.dynamicTable[dynamicTableIndex]); Header header = this.dynamicTable[dynamicTableIndex]; boolean bool = false; list.add(header);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private final int dynamicTableIndex(int index) {
/* 251 */       return this.nextHeaderIndex + 1 + index;
/*     */     }
/*     */ 
/*     */     
/*     */     private final void readLiteralHeaderWithoutIndexingIndexedName(int index) throws IOException {
/* 256 */       ByteString name = getName(index);
/* 257 */       ByteString value = readByteString();
/* 258 */       this.headerList.add(new Header(name, value));
/*     */     }
/*     */ 
/*     */     
/*     */     private final void readLiteralHeaderWithoutIndexingNewName() throws IOException {
/* 263 */       ByteString name = Hpack.INSTANCE.checkLowercase(readByteString());
/* 264 */       ByteString value = readByteString();
/* 265 */       this.headerList.add(new Header(name, value));
/*     */     }
/*     */ 
/*     */     
/*     */     private final void readLiteralHeaderWithIncrementalIndexingIndexedName(int nameIndex) throws IOException {
/* 270 */       ByteString name = getName(nameIndex);
/* 271 */       ByteString value = readByteString();
/* 272 */       insertIntoDynamicTable(-1, new Header(name, value));
/*     */     }
/*     */ 
/*     */     
/*     */     private final void readLiteralHeaderWithIncrementalIndexingNewName() throws IOException {
/* 277 */       ByteString name = Hpack.INSTANCE.checkLowercase(readByteString());
/* 278 */       ByteString value = readByteString();
/* 279 */       insertIntoDynamicTable(-1, new Header(name, value));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final ByteString getName(int index) throws IOException {
/* 287 */       int dynamicTableIndex = dynamicTableIndex(index - (Hpack.INSTANCE.getSTATIC_HEADER_TABLE()).length);
/* 288 */       if (dynamicTableIndex < 0 || dynamicTableIndex >= this.dynamicTable.length) {
/* 289 */         throw (Throwable)new IOException("Header index too large " + (index + 1));
/*     */       }
/*     */       
/* 292 */       Intrinsics.checkNotNull(this.dynamicTable[dynamicTableIndex]); return isStaticHeader(index) ? (Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[index]).name : (this.dynamicTable[dynamicTableIndex]).name;
/*     */     }
/*     */ 
/*     */     
/*     */     private final boolean isStaticHeader(int index) {
/* 297 */       return (index >= 0 && index <= (Hpack.INSTANCE.getSTATIC_HEADER_TABLE()).length - 1);
/*     */     }
/*     */ 
/*     */     
/*     */     private final void insertIntoDynamicTable(int index, Header entry) {
/* 302 */       int i = index;
/* 303 */       this.headerList.add(entry);
/*     */       
/* 305 */       int delta = entry.hpackSize;
/* 306 */       if (i != -1) {
/* 307 */         Intrinsics.checkNotNull(this.dynamicTable[dynamicTableIndex(i)]); delta -= (this.dynamicTable[dynamicTableIndex(i)]).hpackSize;
/*     */       } 
/*     */ 
/*     */       
/* 311 */       if (delta > this.maxDynamicTableByteCount) {
/* 312 */         clearDynamicTable();
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 317 */       int bytesToRecover = this.dynamicTableByteCount + delta - this.maxDynamicTableByteCount;
/* 318 */       int entriesEvicted = evictToRecoverBytes(bytesToRecover);
/*     */       
/* 320 */       if (i == -1) {
/* 321 */         if (this.headerCount + 1 > this.dynamicTable.length) {
/* 322 */           Header[] doubled = new Header[this.dynamicTable.length * 2];
/* 323 */           System.arraycopy(this.dynamicTable, 0, doubled, this.dynamicTable.length, this.dynamicTable.length);
/* 324 */           this.nextHeaderIndex = this.dynamicTable.length - 1;
/* 325 */           this.dynamicTable = doubled;
/*     */         }  int j;
/* 327 */         this.nextHeaderIndex = (j = this.nextHeaderIndex) + -1; i = j;
/* 328 */         this.dynamicTable[i] = entry;
/* 329 */         this.headerCount = (j = this.headerCount) + 1;
/*     */       } else {
/* 331 */         i += dynamicTableIndex(i) + entriesEvicted;
/* 332 */         this.dynamicTable[i] = entry;
/*     */       } 
/* 334 */       this.dynamicTableByteCount += delta;
/*     */     }
/*     */ 
/*     */     
/*     */     private final int readByte() throws IOException {
/* 339 */       return Util.and(this.source.readByte(), 255);
/*     */     }
/*     */ 
/*     */     
/*     */     public final int readInt(int firstByte, int prefixMask) throws IOException {
/* 344 */       int b, prefix = firstByte & prefixMask;
/* 345 */       if (prefix < prefixMask) {
/* 346 */         return prefix;
/*     */       }
/*     */ 
/*     */       
/* 350 */       int result = prefixMask;
/* 351 */       int shift = 0;
/*     */       while (true) {
/* 353 */         b = readByte();
/* 354 */         if ((b & 0x80) != 0) {
/* 355 */           result += (b & 0x7F) << shift;
/* 356 */           shift += 7; continue;
/*     */         }  break;
/* 358 */       }  result += b << shift;
/*     */ 
/*     */ 
/*     */       
/* 362 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public final ByteString readByteString() throws IOException {
/* 368 */       int firstByte = readByte();
/* 369 */       boolean huffmanDecode = ((firstByte & 0x80) == 128);
/* 370 */       long length = readInt(firstByte, 127);
/*     */ 
/*     */       
/* 373 */       Buffer decodeBuffer = new Buffer();
/* 374 */       Huffman.INSTANCE.decode(this.source, length, (BufferedSink)decodeBuffer);
/* 375 */       return huffmanDecode ? decodeBuffer.readByteString() : 
/*     */         
/* 377 */         this.source.readByteString(length);
/*     */     }
/*     */     @JvmOverloads
/*     */     public Reader(@NotNull Source source, int headerTableSizeSetting) {
/*     */       this(source, headerTableSizeSetting, 0, 4, null);
/*     */     } }
/* 383 */   private final Map<ByteString, Integer> nameToFirstIndex() { LinkedHashMap<Object, Object> result = new LinkedHashMap<>(STATIC_HEADER_TABLE.length); byte b; int i;
/* 384 */     for (b = 0, i = STATIC_HEADER_TABLE.length; b < i; b++) {
/* 385 */       if (!result.containsKey((STATIC_HEADER_TABLE[b]).name)) {
/* 386 */         result.put((STATIC_HEADER_TABLE[b]).name, Integer.valueOf(b));
/*     */       }
/*     */     } 
/* 389 */     Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableMap(result), "Collections.unmodifiableMap(result)"); return (Map)Collections.unmodifiableMap(result); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000B\n\002\030\002\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\013\n\000\n\002\030\002\n\002\b\002\n\002\020\021\n\002\030\002\n\002\b\b\n\002\020\002\n\002\b\b\n\002\030\002\n\002\b\002\n\002\020 \n\002\b\005\030\0002\0020\001B#\b\007\022\b\b\002\020\002\032\0020\003\022\b\b\002\020\004\032\0020\005\022\006\020\006\032\0020\007¢\006\002\020\bJ\b\020\023\032\0020\024H\002J\b\020\025\032\0020\024H\002J\020\020\026\032\0020\0032\006\020\027\032\0020\003H\002J\020\020\030\032\0020\0242\006\020\031\032\0020\013H\002J\016\020\032\032\0020\0242\006\020\002\032\0020\003J\016\020\033\032\0020\0242\006\020\034\032\0020\035J\024\020\036\032\0020\0242\f\020\037\032\b\022\004\022\0020\0130 J\036\020!\032\0020\0242\006\020\"\032\0020\0032\006\020#\032\0020\0032\006\020$\032\0020\003R\034\020\t\032\n\022\006\022\004\030\0010\0130\n8\006@\006X\016¢\006\004\n\002\020\fR\022\020\r\032\0020\0038\006@\006X\016¢\006\002\n\000R\016\020\016\032\0020\005X\016¢\006\002\n\000R\022\020\017\032\0020\0038\006@\006X\016¢\006\002\n\000R\022\020\002\032\0020\0038\006@\006X\016¢\006\002\n\000R\022\020\020\032\0020\0038\006@\006X\016¢\006\002\n\000R\016\020\021\032\0020\003X\016¢\006\002\n\000R\016\020\006\032\0020\007X\004¢\006\002\n\000R\016\020\022\032\0020\003X\016¢\006\002\n\000R\016\020\004\032\0020\005X\004¢\006\002\n\000¨\006%"}, d2 = {"Lokhttp3/internal/http2/Hpack$Writer;", "", "headerTableSizeSetting", "", "useCompression", "", "out", "Lokio/Buffer;", "(IZLokio/Buffer;)V", "dynamicTable", "", "Lokhttp3/internal/http2/Header;", "[Lokhttp3/internal/http2/Header;", "dynamicTableByteCount", "emitDynamicTableSizeUpdate", "headerCount", "maxDynamicTableByteCount", "nextHeaderIndex", "smallestHeaderTableSizeSetting", "adjustDynamicTableByteCount", "", "clearDynamicTable", "evictToRecoverBytes", "bytesToRecover", "insertIntoDynamicTable", "entry", "resizeHeaderTable", "writeByteString", "data", "Lokio/ByteString;", "writeHeaders", "headerBlock", "", "writeInt", "value", "prefixMask", "bits", "okhttp"}) public static final class Writer { private int smallestHeaderTableSizeSetting; private boolean emitDynamicTableSizeUpdate; @JvmField public int maxDynamicTableByteCount; @JvmField
/*     */     @NotNull
/*     */     public Header[] dynamicTable; private int nextHeaderIndex; @JvmOverloads
/* 392 */     public Writer(int headerTableSizeSetting, boolean useCompression, @NotNull Buffer out) { this.headerTableSizeSetting = headerTableSizeSetting; this.useCompression = useCompression; this.out = out;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 401 */       this.smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
/*     */       
/* 403 */       this.maxDynamicTableByteCount = this.headerTableSizeSetting;
/*     */ 
/*     */       
/* 406 */       this.dynamicTable = new Header[8];
/*     */       
/* 408 */       this.nextHeaderIndex = this.dynamicTable.length - 1; }
/*     */      @JvmField
/*     */     public int headerCount; @JvmField
/*     */     public int dynamicTableByteCount; @JvmField
/*     */     public int headerTableSizeSetting; private final boolean useCompression; private final Buffer out; private final void clearDynamicTable() {
/* 413 */       ArraysKt.fill$default((Object[])this.dynamicTable, null, 0, 0, 6, null);
/* 414 */       this.nextHeaderIndex = this.dynamicTable.length - 1;
/* 415 */       this.headerCount = 0;
/* 416 */       this.dynamicTableByteCount = 0;
/*     */     }
/*     */ 
/*     */     
/*     */     private final int evictToRecoverBytes(int bytesToRecover) {
/* 421 */       int i = bytesToRecover;
/* 422 */       int entriesToEvict = 0;
/* 423 */       if (i > 0) {
/*     */         
/* 425 */         int j = this.dynamicTable.length - 1;
/* 426 */         while (j >= this.nextHeaderIndex && i > 0) {
/* 427 */           Intrinsics.checkNotNull(this.dynamicTable[j]); i -= (this.dynamicTable[j]).hpackSize;
/* 428 */           Intrinsics.checkNotNull(this.dynamicTable[j]); this.dynamicTableByteCount -= (this.dynamicTable[j]).hpackSize; int k;
/* 429 */           this.headerCount = (k = this.headerCount) + -1;
/* 430 */           entriesToEvict++;
/* 431 */           j--;
/*     */         } 
/* 433 */         System.arraycopy(this.dynamicTable, this.nextHeaderIndex + 1, this.dynamicTable, 
/* 434 */             this.nextHeaderIndex + 1 + entriesToEvict, this.headerCount);
/* 435 */         Arrays.fill((Object[])this.dynamicTable, this.nextHeaderIndex + 1, this.nextHeaderIndex + 1 + entriesToEvict, (Object)null);
/* 436 */         this.nextHeaderIndex += entriesToEvict;
/*     */       } 
/* 438 */       return entriesToEvict;
/*     */     }
/*     */     
/*     */     private final void insertIntoDynamicTable(Header entry) {
/* 442 */       int delta = entry.hpackSize;
/*     */ 
/*     */       
/* 445 */       if (delta > this.maxDynamicTableByteCount) {
/* 446 */         clearDynamicTable();
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 451 */       int bytesToRecover = this.dynamicTableByteCount + delta - this.maxDynamicTableByteCount;
/* 452 */       evictToRecoverBytes(bytesToRecover);
/*     */       
/* 454 */       if (this.headerCount + 1 > this.dynamicTable.length) {
/* 455 */         Header[] doubled = new Header[this.dynamicTable.length * 2];
/* 456 */         System.arraycopy(this.dynamicTable, 0, doubled, this.dynamicTable.length, this.dynamicTable.length);
/* 457 */         this.nextHeaderIndex = this.dynamicTable.length - 1;
/* 458 */         this.dynamicTable = doubled;
/*     */       }  int i;
/* 460 */       this.nextHeaderIndex = (i = this.nextHeaderIndex) + -1; int index = i;
/* 461 */       this.dynamicTable[index] = entry;
/* 462 */       this.headerCount = (i = this.headerCount) + 1;
/* 463 */       this.dynamicTableByteCount += delta;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public final void writeHeaders(@NotNull List<Header> headerBlock) throws IOException {
/* 470 */       Intrinsics.checkNotNullParameter(headerBlock, "headerBlock"); if (this.emitDynamicTableSizeUpdate) {
/* 471 */         if (this.smallestHeaderTableSizeSetting < this.maxDynamicTableByteCount)
/*     */         {
/* 473 */           writeInt(this.smallestHeaderTableSizeSetting, 31, 32);
/*     */         }
/* 475 */         this.emitDynamicTableSizeUpdate = false;
/* 476 */         this.smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
/* 477 */         writeInt(this.maxDynamicTableByteCount, 31, 32);
/*     */       }  byte b;
/*     */       int i;
/* 480 */       for (b = 0, i = headerBlock.size(); b < i; b++) {
/* 481 */         Header header = headerBlock.get(b);
/* 482 */         ByteString name = header.name.toAsciiLowercase();
/* 483 */         ByteString value = header.value;
/* 484 */         int headerIndex = -1;
/* 485 */         int headerNameIndex = -1;
/*     */         
/* 487 */         Integer staticIndex = Hpack.INSTANCE.getNAME_TO_FIRST_INDEX().get(name);
/* 488 */         if (staticIndex != null) {
/* 489 */           headerNameIndex = staticIndex.intValue() + 1;
/* 490 */           int j = headerNameIndex; if (2 > j) { 7; } else if (7 >= j)
/*     */           
/*     */           { 
/*     */ 
/*     */             
/* 495 */             if (Intrinsics.areEqual((Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[headerNameIndex - 1]).value, value)) {
/* 496 */               headerIndex = headerNameIndex;
/* 497 */             } else if (Intrinsics.areEqual((Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[headerNameIndex]).value, value)) {
/* 498 */               headerIndex = headerNameIndex + 1;
/*     */             }  }
/*     */         
/*     */         } 
/*     */         
/* 503 */         if (headerIndex == -1) {
/* 504 */           for (int j = this.nextHeaderIndex + 1, k = this.dynamicTable.length; j < k; j++) {
/* 505 */             Intrinsics.checkNotNull(this.dynamicTable[j]); if (Intrinsics.areEqual((this.dynamicTable[j]).name, name)) {
/* 506 */               Intrinsics.checkNotNull(this.dynamicTable[j]); if (Intrinsics.areEqual((this.dynamicTable[j]).value, value)) {
/* 507 */                 headerIndex = j - this.nextHeaderIndex + (Hpack.INSTANCE.getSTATIC_HEADER_TABLE()).length; break;
/*     */               } 
/* 509 */               if (headerNameIndex == -1) {
/* 510 */                 headerNameIndex = j - this.nextHeaderIndex + (Hpack.INSTANCE.getSTATIC_HEADER_TABLE()).length;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/* 517 */         if (headerIndex != -1) {
/*     */           
/* 519 */           writeInt(headerIndex, 127, 128);
/*     */         }
/* 521 */         else if (headerNameIndex == -1) {
/*     */           
/* 523 */           this.out.writeByte(64);
/* 524 */           writeByteString(name);
/* 525 */           writeByteString(value);
/* 526 */           insertIntoDynamicTable(header);
/*     */         }
/* 528 */         else if (name.startsWith(Header.PSEUDO_PREFIX) && (Intrinsics.areEqual(Header.TARGET_AUTHORITY, name) ^ true) != 0) {
/*     */ 
/*     */           
/* 531 */           writeInt(headerNameIndex, 15, 0);
/* 532 */           writeByteString(value);
/*     */         }
/*     */         else {
/*     */           
/* 536 */           writeInt(headerNameIndex, 63, 64);
/* 537 */           writeByteString(value);
/* 538 */           insertIntoDynamicTable(header);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final void writeInt(int value, int prefixMask, int bits) {
/* 546 */       int i = value;
/*     */       
/* 548 */       if (i < prefixMask) {
/* 549 */         this.out.writeByte(bits | i);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 554 */       this.out.writeByte(bits | prefixMask);
/* 555 */       i -= prefixMask;
/*     */ 
/*     */       
/* 558 */       while (i >= 128) {
/* 559 */         int b = i & 0x7F;
/* 560 */         this.out.writeByte(b | 0x80);
/* 561 */         i >>>= 7;
/*     */       } 
/* 563 */       this.out.writeByte(i);
/*     */     }
/*     */ 
/*     */     
/*     */     public final void writeByteString(@NotNull ByteString data) throws IOException {
/* 568 */       Intrinsics.checkNotNullParameter(data, "data"); if (this.useCompression && Huffman.INSTANCE.encodedLength(data) < data.size()) {
/* 569 */         Buffer huffmanBuffer = new Buffer();
/* 570 */         Huffman.INSTANCE.encode(data, (BufferedSink)huffmanBuffer);
/* 571 */         ByteString huffmanBytes = huffmanBuffer.readByteString();
/* 572 */         writeInt(huffmanBytes.size(), 127, 128);
/* 573 */         this.out.write(huffmanBytes);
/*     */       } else {
/* 575 */         writeInt(data.size(), 127, 0);
/* 576 */         this.out.write(data);
/*     */       } 
/*     */     }
/*     */     
/*     */     public final void resizeHeaderTable(int headerTableSizeSetting) {
/* 581 */       this.headerTableSizeSetting = headerTableSizeSetting;
/* 582 */       int i = 16384; boolean bool = false; int effectiveHeaderTableSize = Math.min(headerTableSizeSetting, i);
/*     */       
/* 584 */       if (this.maxDynamicTableByteCount == effectiveHeaderTableSize)
/*     */         return; 
/* 586 */       if (effectiveHeaderTableSize < this.maxDynamicTableByteCount) {
/*     */         
/* 588 */         i = this.smallestHeaderTableSizeSetting; bool = false; this.smallestHeaderTableSizeSetting = Math.min(i, effectiveHeaderTableSize);
/*     */       } 
/* 590 */       this.emitDynamicTableSizeUpdate = true;
/* 591 */       this.maxDynamicTableByteCount = effectiveHeaderTableSize;
/* 592 */       adjustDynamicTableByteCount();
/*     */     }
/*     */     
/*     */     private final void adjustDynamicTableByteCount() {
/* 596 */       if (this.maxDynamicTableByteCount < this.dynamicTableByteCount)
/* 597 */         if (this.maxDynamicTableByteCount == 0) {
/* 598 */           clearDynamicTable();
/*     */         } else {
/* 600 */           evictToRecoverBytes(this.dynamicTableByteCount - this.maxDynamicTableByteCount);
/*     */         }  
/*     */     } @JvmOverloads
/*     */     public Writer(int headerTableSizeSetting, @NotNull Buffer out) {
/*     */       this(headerTableSizeSetting, false, out, 2, null);
/*     */     }
/*     */     @JvmOverloads
/*     */     public Writer(@NotNull Buffer out) {
/*     */       this(0, false, out, 3, null);
/*     */     } }
/*     */   @NotNull
/*     */   public final ByteString checkLowercase(@NotNull ByteString name) throws IOException {
/* 612 */     Intrinsics.checkNotNullParameter(name, "name"); byte b; int i; for (b = 0, i = name.size(); b < i; b++) {
/* 613 */       byte b1 = name.getByte(b); if ((byte)65 > b1) { (byte)90; } else if ((byte)90 >= b1)
/* 614 */       { throw (Throwable)new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + name.utf8()); }
/*     */     
/*     */     } 
/* 617 */     return name;
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http2/Hpack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */