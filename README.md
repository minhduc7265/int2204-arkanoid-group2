# Arkanoid
# Giới thiệu
Arkanoid là một trò chơi nơi người chơi sử dụng thanh trượt và quả bóng để phá hủy gạch trên màn hình   
Mục tiêu của game sẽ là phá hủy hết gạch và không được để mất quá nhiều mạng   

Mình sẽ giới thiệu game và chúng gồm các phần như sau   
📌 Phần 1: Giới thiệu thành viên      
📌 Phần 2: Tải game   
📌 Phần 3: Giới thiệu thư viện sử dụng   
📌 Phần 4: Biểu đồ UML và cấu trúc Game   
📌 Phần 5: Các kỹ thuật sử dụng   
📌 Phần 6: Giới thiệu các vật phẩm trong game   
📌 Phần 7: Giới thiệu các tính năng trong game   
📌 Phần 8: Tài liệu tham khảo   
___
### Phần 1: Giới thiệu thành viên      
- Họ và Tên: **Hoàng Minh Đức** - MSV: **24021412**   
- Họ và Tên: **Trần Nhật Hưng** - MSV: **24020153**   
- Họ và Tên: **Nguyễn Công Huy Hoàng** - MSV: **24021486**   
- Họ và Tên: **Lê Tuấn Anh** - MSV: **24021366**   

___
### Phần 2: Tải game   
Ta có hai cách như sau    
- Cách thứ nhất: PullCode về máy tính cá nhân của bạn và sau đó dùng Intellij để chạy   
- Cách thứ hai: Đơn giản hơn nhiều   
  - Bước 1: Bạn tải file sau và đây chính là file cài đặt của Game [Link](https://drive.google.com/file/d/1I8i2KVhKIk-eZbHDjGCFnVh61dSsrK_p/view?usp=sharing)   
  - Bước 2: Khởi chạy và giải nén ra   
  - Bước 3: Nếu chưa cài máy ảo Java thì bạn nên cài chúng   
  - Bước 4: Khởi chạy file arkanoid.jar   







### Phần 3: Thư viện sử dụng   
- **LibGDX[Thư viện chính]**
  - LibGDX là một framework phát triển trò chơi đa nền tảng bằng Java, nổi bật nhờ khả năng xử lý đồ họa 2D và 3D mượt mà, quản lý âm thanh, ánh sáng, vật lý, cùng với công cụ nhập liệu từ bàn phím, chuột, cảm ứng.   
  - LibGDX là một framework cho phép phát triển trên đa nền tảng như Window/Linux/iOS/Android/Web.   
  - LibGDX hỗ trợ Scene2D giúp xử lí mạnh mẽ về giao diện.   
  - Ngoài ra LibGDX hỗ trợ Viewports giúp xử lí về mặt hiển thị.
  - Box2D cùng với hệ thống va chạm mạnh mẽ giúp làm Game đơn giản hơn.
  - Ngoài ra LibGDX còn rất nhiều tính năng khác mà bạn nên tìm hiểu nếu muốn làm Game bằng java.   
  - Tuy nhiên do tính chất của bài tập lớn nên có thể nhóm mình sẽ không phát huy hết được các tính năng của framework này.   
- **Bass[Thư viện C++]**
  - Bass là thư viện do Un4seen Developments phát triển và ra đời năm 1999.
  - Bass là một thư viện được viết bằng C++.   
  - Bass có chức năng phát âm thanh nhiều định dạng như MP3/OGG/WAV...
  - Thư viện này được sử dụng trong rất nhiều phần mềm và Game nhưng tiêu biểu nhất là Game của PopCap.
  - Điểm đặc biệt của thư viện này mà khiến mình quyết định sử dụng đó chính là hỗ trợ phát nhạc Module[Trong dự án này mình sẽ dùng file mainmusic.mo3 chủ yếu để phát nhạc nền].
  - Nhạc Module là một định dạng khá đặc biệt so với các định dạng khác, nó sẽ sử dụng các nốt nhạc để tạo ra một bản nhạc thay vì phát như file mp3 thông thường.
  - Ở dự án này mình sẽ dùng file Bass.dll kết hợp JNA để phát nhạc.   
- **Java Native Access[JNA]**
  - JNA (Java Native Access) được phát triển bởi Timothy Wall, nhằm đơn giản hóa việc gọi các thư viện native từ Java mà không cần viết code JNI phức tạp. Thư viện này lần đầu ra mắt khoảng năm 2004 và kể từ đó trở thành một công cụ phổ biến cho việc tích hợp Java với các thư viện hệ thống hoặc thư viện C/C++.
  - Cách hoạt động hiểu đơn giản có thể giải thích như sau, JNA sẽ ánh xạ các hàm bên trong file dll vào một class nào đó, từ đó ta có thể sử dụng hàm của file dll một cách đơn giản hơn so với sử dụng JNI.
  - Trong dự án này mình sẽ ánh xạ một số hàm của file Bass.dll từ đó có thể phát nhạc Module.
___
### Phần 4: Biểu đồ UML và cấu trúc Game:
<img width="5567" height="6336" alt="image" src="https://github.com/minhduc7265/readme_/blob/main/UML5.png" />






🔹 1. Package engine
- Chức năng: Quản lí logic va chạm và quản lí trạng thái game
- Các lớp gồm:   
 **AABB, Point, Segment**: Biểu diễn các đối tượng hình học cơ bản để tính toán va chạm.       
 **Collision, CollisionResult**:	Phát hiện và phản ứng va chạm giữa bóng – gạch – paddle.    
 **GameManager**:	Quản lý trạng thái tổng thể của trò chơi (điểm, mạng, cấp độ).
   
🔹 2. Package model – Mô hình đối tượng trong game
- Chức năng: Mỗi lớp là một đối tượng cụ thể trong thế giới Arkanoid, được kế thừa từ GameObject hoặc MovableObject.   
- Các lớp gồm:   
**Ball, Paddle, Brick**: Các đối tượng chính trong gameplay.   
**NormalBrick, StrongBrick**:	Các loại gạch khác nhau (độ bền, hiệu ứng).   
**Item, ItemManager**:	Quản lý vật phẩm rơi ra từ gạch.   
**ExpandPaddleItem, FastBallItem, FireBallItem, BallBreakItem...**:	Vật phẩm tăng/giảm sức mạnh.   
**BrickFactory**: Tạo đối tượng gạch theo loại.

🔹 3. Package world – Quản lý thế giới trò chơi
- Chức năng: Là tầng trung gian giữa logic và render.   
- Các lớp gồm:    
**GameWorld**:	Quản lý toàn bộ các đối tượng, cập nhật trạng thái và vẽ khung hình.   
**CollisionManager**:	Gọi đến engine.Collision để kiểm tra va chạm.   
**LevelLoader**:	Tải bản đồ màn chơi từ dữ liệu trong leveldata.
    
🔹 4. Package leveldata – Dữ liệu và cấu hình màn chơi   
- Chức năng: Cung cấp thông tin cho từng màn và lưu tiến trình người chơi.   
- Các lớp gồm:   
**LevelInfo**:	Thông tin bố trí gạch trong từng màn.   
**BrickInformation**:	Cấu hình chi tiết cho từng ô gạch.   
**GameDataManager**:	Quản lý lưu/trả dữ liệu người chơi.   
**PlayerData**:	Lưu điểm, mạng, cấp độ hiện tại.
   
🔹 5. Package output – Hiển thị, âm thanh và hiệu ứng   
- Chức năng: Xử lý mọi thứ liên quan đến render, animation, button, sound.   
- Các lớp gồm:     
**Renderer, MasterRenderer**:	Kết xuất (render) các đối tượng 2D.   
**AnimationFactory, AnimationRender, AnimationSpriteSheet**:	Tạo và vẽ hoạt ảnh.   
**FontManager, TextObject**:	Quản lý font và hiển thị chữ.   
**TextureManager**:	Quản lý texture, tránh load trùng lặp.   
**SoundManager, MusicManager, Bass**:	Phát âm thanh và nhạc nền.   
**Button**:	Nút tương tác trong menu.

🔹 6. Package screens – Giao diện và điều hướng   

- Chức năng: Tổ chức màn hình khác nhau theo LibGDX.   
- Các lớp gồm:     
**MainMenuScreen**:	Màn hình chính (chơi, cài đặt, thoát).   
**GameScreen**:	Màn hình chơi chính.   
**CreditScreen**:	Hiển thị thông tin nhóm phát triển.   
**ButtonManager**:	Quản lý các nút trong từng màn hình.

🔹 7. Class Main   
- Chức năng: Là nơi khởi chạy ứng dụng   
- **Main**: tạo đối tượng game, khởi tạo LibGDX, thiết lập màn hình đầu tiên.   
___
### Phần 5: Các kỹ thuật sử dụng   
🔹 1. Lập trình hướng đối tượng   
- Game được thiết kế theo hướng đối tượng
    - GameObject, MovableObject: Các lớp cơ sở cho các đối tượng của Game
    - Paddle, Ball, Item,...: Các lớp dẫn xuất kế thừa các lớp cơ sở
    
🔹 2. Sử dụng vòng lặp   
- Sử dụng vòng lặp để duy trì game chạy, cập nhật, hiển thị(updateLogic, Render)

🔹 3. Xử lí va chạm
- Va chạm được tham khảo qua giáo trình Real-Time Collision Detection của Christer Ericson   
    - IntersectMovingSphereAABB(): Hàm phát hiện va chạm tổng thể   
    - IntersectSegmentCircle(): Hàm phát hiện va chạm giữa một hình tròn và một đường thẳng   
    - IntersectRayAABB(): Phát hiện va chạm sử dụng bài toán tổng Minkowski

🔹 4. Quản lí tài nguyên   
- Thay vì load đi load lại liên tục, ta sẽ sử dụng các Manager để quản lí các Texture, Music, Sound, Font

🔹 5. Render và sử dụng Animation
- Render, MasterRender giúp hiển thị các Texture trong game.   
- AnimationSpriteSheet load các spritesheet, chạy các hiệu ứng như phá gạch, bom nổ làm tăng trải nghiệm chơi Game.

🔹 6. Xử lí âm thanh   
- Sử dụng Bass giúp phát nhạc Module.   
- Sử dụng SoundManager cho các hiệu ứng âm thanh tức thời.   

🔹 7. Quản lí giao diện, sự kiện
- Sử dụng Screen tạo ra các màn hình như MainMenuScreen, CreditScreen, GameScreen.
- Kết hợp Stage, Table, Button để xử lí sự kiện bấm nút.

🔹 8. Multithread
- Sử dụng đa luồng trong việc update Item.

🔹 9. Quản lí dữ liệu level
- Sử dụng LevelLoader để load dữ liệu viên gạch từ file json.
- Sử dụng GameDataManager để đọc và ghi dữ liệu người chơi.

🔹 10. Design Patterns
- Sử dụng Singlenton Pattern cho các class như GameManager, TextureManager, SoundManager đảm bảo chúng tồn tại duy nhất trong suốt quá trình Game chạy.
- Sử dụng Factory Pattern cho BrickFactory, AnimationFactory giúp tạo đối tượng dễ dàng, hạn chế lỗi.
- Sử dụng Flyweight Pattern cho các class TextureManager, SoundManager nhằm tái sử dụng các tài nguyên mà không cần phải load lại.

🔹 11. Xử lí ngoại lệ và Junit
- Khi load tài nguyên bị lỗi, ta có thể sử dụng tài nguyên mặc định để tránh crash game, tương tự trong việc ta lấy dữ liệu của tài nguyên[Ví dụ: Khi lấy Texture bị null, ta chủ động sử dụng Texture mặc định để khi render không bị lỗi...]
- Sử dụng JUnit để kiểm thử một số công đoạn[Ví dụ kiểm thử va chạm, kiểm thử di chuyển của MovableObject...]



### Phần 6: Giới thiệu các vật phẩm trong game
| Tên    |   Hình Ảnh      | Loại    | Mô tả    |
|----------|----------|----------|----------|
|Bóng thường|<img src="https://github.com/minhduc7265/int2204-arkanoid-group2/blob/master/assets/textures/ball_1.png" alt="Alt text" width="25" height="25">| Bóng |Gây 1 sát thương cho gạch|
|Bóng lửa|<img src="https://github.com/minhduc7265/int2204-arkanoid-group2/blob/master/assets/textures/ball_2.png" alt="Alt text" width="25" height="25">| Bóng |Gây 1 sát thương đồng thời gây hiệu ứng nổ xung quanh|
|Tim mũi tên đi xuống| <img src="https://github.com/minhduc7265/int2204-arkanoid-group2/blob/master/assets/textures/ball_break.png" alt="Alt text" width="52" height="52">| Item giảm sức mạnh|Khiến bóng vỡ và mất mạng|
|Lọ thuốc| <img src="https://github.com/minhduc7265/int2204-arkanoid-group2/blob/master/assets/textures/ball_invisible.png" alt="Alt text" width="52" height="52">| Item giảm sức mạnh|Khiến bóng bị tàng hình|
|Tim dấu cộng| <img src="https://github.com/minhduc7265/int2204-arkanoid-group2/blob/master/assets/textures/boost_heart.png" alt="Alt text" width="52" height="52">| Item tăng sức mạnh|Tăng 1 mạng|
|Thanh trượt vàng| <img src="https://github.com/minhduc7265/int2204-arkanoid-group2/blob/master/assets/textures/boost_paddle.png" alt="Alt text" width="52" height="52">| Item tăng sức mạnh|Tăng độ dài của thanh trượt|
|Tia sét| <img src="https://github.com/minhduc7265/int2204-arkanoid-group2/blob/master/assets/textures/boost_speed.png" alt="Alt text" width="52" height="52">| Item tăng sức mạnh|Khiến bóng đi nhanh hơn|
|Cầu lửa| <img src="https://github.com/minhduc7265/int2204-arkanoid-group2/blob/master/assets/textures/fire_ball.png" alt="Alt text" width="52" height="52">| Item tăng sức mạnh|Biến bóng thường thành bóng lửa|
|Gạch xanh| <img src="https://github.com/minhduc7265/int2204-arkanoid-group2/blob/master/assets/textures/brick_1.png" alt="Alt text" width="52" height="52">| Gạch|Gạch 1 máu, khi phá nhận 50 điểm|
|Gạch hồng| <img src="https://github.com/minhduc7265/int2204-arkanoid-group2/blob/master/assets/textures/brick_2.png" alt="Alt text" width="52" height="52">| Gạch|Gạch 1 máu, khi phá nhận 60 điểm|
|Gạch cam| <img src="https://github.com/minhduc7265/int2204-arkanoid-group2/blob/master/assets/textures/brick_3.png" alt="Alt text" width="52" height="52">| Gạch|Gạch 1 máu, khi phá nhận 70 điểm|
|Gạch đỏ| <img src="https://github.com/minhduc7265/int2204-arkanoid-group2/blob/master/assets/textures/brick_4.png" alt="Alt text" width="52" height="52">| Gạch|Gạch 1 máu, khi phá nhận 90 điểm|
|Gạch đen| <img src="https://github.com/minhduc7265/int2204-arkanoid-group2/blob/master/assets/textures/brick_5.png" alt="Alt text" width="52" height="52">| Gạch|Gạch 2 máu, khi phá nhận 120 điểm|
|Gạch vàng| <img src="https://github.com/minhduc7265/int2204-arkanoid-group2/blob/master/assets/textures/brick_6.png" alt="Alt text" width="52" height="52">| Gạch|Gạch bất tử, không thể phá|
|Thanh trượt| <img src="https://github.com/minhduc7265/int2204-arkanoid-group2/blob/master/assets/textures/paddle.png" alt="Alt text" width="52" height="52">| Thanh trượt|Dùng để điều khiển bóng|


### Phần 7: Giới thiệu các tính năng trong game
- Điều khiển thanh trượt: Người chơi dùng chuột để điều khiển bắt bóng hợp lí.   
- Bóng nảy: Bóng khi đâp vào tường, thanh trượt hay gạch sẽ bị nảy.      
- Phá gạch: Khi bóng va vào gạch, gạch sẽ bị trừ máu và nếu xuống 0 thì gạch sẽ bị phá hủy hoàn toàn[Trừ gạch bất tử].   
- Tính điểm: Khi gạch bị phá hủy, ta nhận được điểm số tương ứng.  
- Màn chơi: Người chơi khi phá đảo level sẽ được chuyển sang level khác với bố cục gạch khác.   
- Thua cuộc: Khi mất hết mạng, người chơi sẽ thua.   
- Vật phẩm(Item): Chính là PowerUp hoặc Debuff, vật phẩm cung cấp sức mạnh hoặc ngược lại gây khó khăn cho người chơi.   
- Nhạc nền/Âm thanh: Âm thanh khi va chạm hoặc nhạc nền của game giúp tăng trải nghiệm người dùng.   
- Lưu điểm cao: Khi người chơi đạt điểm cao hơn nhưng với điều kiện không được thoát game giữa chừng thì game sẽ lưu điểm cao nhất.   
- Hệ thống mạng sống: Người chơi bị giới hạn mạng, nếu hết thì bị thua tuy nhiên cũng có thể tăng mạng bằng cách ăn trái tim.
- Va chạm: Sử dụng hệ thống va chạm để check va chạm giữa các vật thể nhằm mô tả vật lí trong game.
  
### Phần 8: Tài liệu tham khảo
🔹Bass: Tài liệu API của BASS [Link](https://www.un4seen.com/doc/)   
🔹LibGDX: Tài liệu API của LibGDX [Link](https://libgdx.com/wiki/)   
🔹JNA: Tài liệu API của JNA [Link](https://java-native-access.github.io/jna/4.2.0/)   
🔹Các tài nguyên trong Game: 时空环游之旅, Plants vs. Zombies 2, Peggle, Brick Inc, Brick Breaker.   
🔹Va chạm: Giáo trình Real-Time Collision Detection của Christer Ericson. [Link](https://www.r-5.org/files/books/computers/algo-list/realtime-3d/Christer_Ericson-Real-Time_Collision_Detection-EN.pdf)      
<img width="162" height="200" alt="image" src="https://github.com/user-attachments/assets/923dae37-6d08-40d0-a4d6-5d8c2159f424" />





