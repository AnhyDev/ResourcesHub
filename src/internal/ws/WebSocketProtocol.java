/*     */ package okhttp3.internal.ws;
/*     */ 
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okio.Buffer;
/*     */ import okio.ByteString;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0004\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\b\n\002\b\b\n\002\020\t\n\002\b\021\n\002\020\002\n\000\n\002\030\002\n\002\020\022\n\002\b\002\bÆ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J\016\020\034\032\0020\0042\006\020\035\032\0020\004J\020\020\036\032\004\030\0010\0042\006\020\037\032\0020\006J\026\020 \032\0020!2\006\020\"\032\0020#2\006\020\035\032\0020$J\016\020%\032\0020!2\006\020\037\032\0020\006R\016\020\003\032\0020\004XT¢\006\002\n\000R\016\020\005\032\0020\006XT¢\006\002\n\000R\016\020\007\032\0020\006XT¢\006\002\n\000R\016\020\b\032\0020\006XT¢\006\002\n\000R\016\020\t\032\0020\006XT¢\006\002\n\000R\016\020\n\032\0020\006XT¢\006\002\n\000R\016\020\013\032\0020\006XT¢\006\002\n\000R\016\020\f\032\0020\006XT¢\006\002\n\000R\016\020\r\032\0020\006XT¢\006\002\n\000R\016\020\016\032\0020\017XT¢\006\002\n\000R\016\020\020\032\0020\006XT¢\006\002\n\000R\016\020\021\032\0020\006XT¢\006\002\n\000R\016\020\022\032\0020\006XT¢\006\002\n\000R\016\020\023\032\0020\006XT¢\006\002\n\000R\016\020\024\032\0020\006XT¢\006\002\n\000R\016\020\025\032\0020\006XT¢\006\002\n\000R\016\020\026\032\0020\006XT¢\006\002\n\000R\016\020\027\032\0020\006XT¢\006\002\n\000R\016\020\030\032\0020\017XT¢\006\002\n\000R\016\020\031\032\0020\006XT¢\006\002\n\000R\016\020\032\032\0020\006XT¢\006\002\n\000R\016\020\033\032\0020\017XT¢\006\002\n\000¨\006&"}, d2 = {"Lokhttp3/internal/ws/WebSocketProtocol;", "", "()V", "ACCEPT_MAGIC", "", "B0_FLAG_FIN", "", "B0_FLAG_RSV1", "B0_FLAG_RSV2", "B0_FLAG_RSV3", "B0_MASK_OPCODE", "B1_FLAG_MASK", "B1_MASK_LENGTH", "CLOSE_CLIENT_GOING_AWAY", "CLOSE_MESSAGE_MAX", "", "CLOSE_NO_STATUS_CODE", "OPCODE_BINARY", "OPCODE_CONTINUATION", "OPCODE_CONTROL_CLOSE", "OPCODE_CONTROL_PING", "OPCODE_CONTROL_PONG", "OPCODE_FLAG_CONTROL", "OPCODE_TEXT", "PAYLOAD_BYTE_MAX", "PAYLOAD_LONG", "PAYLOAD_SHORT", "PAYLOAD_SHORT_MAX", "acceptHeader", "key", "closeCodeExceptionMessage", "code", "toggleMask", "", "cursor", "Lokio/Buffer$UnsafeCursor;", "", "validateCloseCode", "okhttp"})
/*     */ public final class WebSocketProtocol
/*     */ {
/*     */   @NotNull
/*     */   public static final String ACCEPT_MAGIC = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
/*     */   public static final int B0_FLAG_FIN = 128;
/*     */   public static final int B0_FLAG_RSV1 = 64;
/*     */   public static final int B0_FLAG_RSV2 = 32;
/*     */   public static final int B0_FLAG_RSV3 = 16;
/*     */   
/*     */   static {
/*  21 */     WebSocketProtocol webSocketProtocol = new WebSocketProtocol();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int B0_MASK_OPCODE = 15;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int OPCODE_FLAG_CONTROL = 8;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int B1_FLAG_MASK = 128;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int B1_MASK_LENGTH = 127;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int OPCODE_CONTINUATION = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int OPCODE_TEXT = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int OPCODE_BINARY = 2;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int OPCODE_CONTROL_CLOSE = 8;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int OPCODE_CONTROL_PING = 9;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int OPCODE_CONTROL_PONG = 10;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final long PAYLOAD_BYTE_MAX = 125L;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final long CLOSE_MESSAGE_MAX = 123L;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int PAYLOAD_SHORT = 126;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final long PAYLOAD_SHORT_MAX = 65535L;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int PAYLOAD_LONG = 127;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int CLOSE_CLIENT_GOING_AWAY = 1001;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int CLOSE_NO_STATUS_CODE = 1005;
/*     */ 
/*     */   
/*     */   public static final WebSocketProtocol INSTANCE;
/*     */ 
/*     */ 
/*     */   
/*     */   public final void toggleMask(@NotNull Buffer.UnsafeCursor cursor, @NotNull byte[] key) {
/*  99 */     Intrinsics.checkNotNullParameter(cursor, "cursor"); Intrinsics.checkNotNullParameter(key, "key"); int keyIndex = 0;
/* 100 */     int keyLength = key.length;
/*     */     do {
/* 102 */       byte[] buffer = cursor.data;
/* 103 */       int i = cursor.start;
/* 104 */       int end = cursor.end;
/* 105 */       if (buffer == null)
/* 106 */         continue;  while (i < end) {
/* 107 */         keyIndex %= keyLength;
/*     */ 
/*     */ 
/*     */         
/* 111 */         int bufferInt = buffer[i];
/* 112 */         int keyInt = key[keyIndex];
/* 113 */         buffer[i] = (byte)(bufferInt ^ keyInt);
/*     */         
/* 115 */         i++;
/* 116 */         keyIndex++;
/*     */       }
/*     */     
/* 119 */     } while (cursor.next() != -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public final String closeCodeExceptionMessage(int code) {
/*     */     // Byte code:
/*     */     //   0: iload_1
/*     */     //   1: sipush #1000
/*     */     //   4: if_icmplt -> 14
/*     */     //   7: iload_1
/*     */     //   8: sipush #5000
/*     */     //   11: if_icmplt -> 36
/*     */     //   14: new java/lang/StringBuilder
/*     */     //   17: dup
/*     */     //   18: invokespecial <init> : ()V
/*     */     //   21: ldc 'Code must be in range [1000,5000): '
/*     */     //   23: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   26: iload_1
/*     */     //   27: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*     */     //   30: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   33: goto -> 104
/*     */     //   36: sipush #1006
/*     */     //   39: sipush #1004
/*     */     //   42: iload_1
/*     */     //   43: istore_2
/*     */     //   44: iload_2
/*     */     //   45: if_icmpgt -> 55
/*     */     //   48: iload_2
/*     */     //   49: if_icmpge -> 76
/*     */     //   52: goto -> 56
/*     */     //   55: pop
/*     */     //   56: sipush #2999
/*     */     //   59: sipush #1015
/*     */     //   62: iload_1
/*     */     //   63: istore_2
/*     */     //   64: iload_2
/*     */     //   65: if_icmple -> 72
/*     */     //   68: pop
/*     */     //   69: goto -> 103
/*     */     //   72: iload_2
/*     */     //   73: if_icmplt -> 103
/*     */     //   76: new java/lang/StringBuilder
/*     */     //   79: dup
/*     */     //   80: invokespecial <init> : ()V
/*     */     //   83: ldc 'Code '
/*     */     //   85: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   88: iload_1
/*     */     //   89: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*     */     //   92: ldc ' is reserved and may not be used.'
/*     */     //   94: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   97: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   100: goto -> 104
/*     */     //   103: aconst_null
/*     */     //   104: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #123	-> 0
/*     */     //   #124	-> 14
/*     */     //   #125	-> 36
/*     */     //   #126	-> 76
/*     */     //   #128	-> 103
/*     */     //   #125	-> 104
/*     */     //   #123	-> 104
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	105	0	this	Lokhttp3/internal/ws/WebSocketProtocol;
/*     */     //   0	105	1	code	I
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void validateCloseCode(int code) {
/* 133 */     String message = closeCodeExceptionMessage(code);
/* 134 */     boolean bool1 = (message == null) ? true : false, bool2 = false, bool3 = false; if (!bool1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 142 */       int $i$a$-require-WebSocketProtocol$validateCloseCode$1 = 0;
/*     */       Intrinsics.checkNotNull(message);
/*     */       String str = message;
/*     */       throw (Throwable)new IllegalArgumentException(str.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public final String acceptHeader(@NotNull String key) {
/*     */     Intrinsics.checkNotNullParameter(key, "key");
/*     */     return ByteString.Companion.encodeUtf8(key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").sha1().base64();
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/ws/WebSocketProtocol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */