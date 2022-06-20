<%@  page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>벽돌게임</title>
<style>
    	* { padding: 0; margin: 0; }
    	
    	canvas { background: #eee; display: block; margin: 0 auto; }
</style>
</head>
<body>

<canvas id="myCanvas" width="480" height="320"> </canvas>

<button type="button" onclick="location.href='<%=request.getContextPath() %>/board/main.do'">메인페이지</button>

<script>
	// JavaScript 코드가 여기에 들어갈 것입니다.
	
	var canvas = document.getElementById("myCanvas"); //myCanvas라는 id를 가진 canvas를 canvas에 담음
	var ctx = canvas.getContext("2d");//contextType을 2d로 지정하여 CanvasRenderingContext2D 객체를 ctx로 받는다.
	var x = canvas.width/2; //x좌표
	var y = canvas.height-30; //y좌표
	var dx = 2; //x축에서 2만큼 움직임
	var dy = -2; //y축에서 -2만큼 움직임
	var ballRadius = 10; //공모서리 10도
	
	var paddleHeight = 10;
	var paddleWidth = 75;
	var paddleX = (canvas.width-paddleWidth)/2;
	var rightPressed = false;
	var leftPressed = false;
	
	var brickRowCount = 3;
	var brickColumnCount = 5;
	var brickWidth = 75;
	var brickHeight = 20;
	var brickPadding = 10;
	var brickOffsetTop = 30;
	var brickOffsetLeft = 30;
	
	var score = 0; //점수
	
	var lives = 3;//목숨
	
	var bricks = [];
		for(var c=0; c<brickColumnCount; c++) {
    		bricks[c] = [];
    	for(var r=0; r<brickRowCount; r++) {
        	bricks[c][r] = { x: 0, y: 0, status: 1};
    	}
	}
	
	function drawBricks() { //벽돌 생성
    for(var c=0; c<brickColumnCount; c++) {
        for(var r=0; r<brickRowCount; r++) {
        	if(bricks[c][r].status == 1) {
            	var brickX = (c*(brickWidth+brickPadding))+brickOffsetLeft;
            	var brickY = (r*(brickHeight+brickPadding))+brickOffsetTop;
            	bricks[c][r].x = brickX;
            	bricks[c][r].y = brickY;
            	ctx.beginPath();
           		ctx.rect(brickX, brickY, brickWidth, brickHeight);
            	ctx.fillStyle = "#0095DD";
            	ctx.fill();
            	ctx.closePath();
            }
        }
    }
}
	//이벤트
document.addEventListener("keydown", keyDownHandler, false);
document.addEventListener("keyup", keyUpHandler, false);
document.addEventListener("mousemove", mouseMoveHandler, false);

function mouseMoveHandler(e) {
    var relativeX = e.clientX - canvas.offsetLeft;
    if(relativeX > 0 && relativeX < canvas.width) {
        paddleX = relativeX - paddleWidth/2; //마우스 포인터와 패들중심부를 일치시킴.
    }
}


function keyDownHandler(e) {
    if(e.keyCode == 39) {
        rightPressed = true;
    }
    else if(e.keyCode == 37) {
        leftPressed = true;
    }
}

function keyUpHandler(e) {
    if(e.keyCode == 39) {
        rightPressed = false;
    }
    else if(e.keyCode == 37) {
        leftPressed = false;
    }
}

function collisionDetection() {
    for(var c=0; c<brickColumnCount; c++) {
        for(var r=0; r<brickRowCount; r++) {
            var b = bricks[c][r];
            if(b.status == 1) {
            	if(x > b.x && x < b.x+brickWidth && y > b.y && y < b.y+brickHeight) { //벽돌과 부딫힐때
                	dy = -dy; //이동방향 반대로
                	 b.status = 0; //상태를 0, 존재하지 않음으로 만들고
                	 score++; //점수증가
                	 if(score == brickRowCount*brickColumnCount) { //점수가 벽돌에 할당된 점수와 같았지면
                        alert("YOU WIN, CONGRATULATIONS!"+"Score: "+ score); //승리메시지 출력
                        document.location.reload(); //리로드한다.
                    }
                }
            }
        }
    }
}

function drawScore() { //점수
    ctx.font = "16px Arial"; //글꼴 글씨크기
    ctx.fillStyle = "#0095DD"; //글색
    ctx.fillText("Score: "+score, 8, 20); //내용, 배치될 위치 x,y
}

function drawLives() { //목숨
    ctx.font = "16px Arial";
    ctx.fillStyle = "#0095DD";
    ctx.fillText("Lives: "+lives, canvas.width-65, 20);
}

function drawBall() {
    ctx.beginPath();
    ctx.arc(x, y, ballRadius, 0, Math.PI*2);
    ctx.fillStyle = "#0095DD";
    ctx.fill();
    ctx.closePath();
}

function drawPaddle() {
    ctx.beginPath();
    ctx.rect(paddleX, canvas.height-paddleHeight, paddleWidth, paddleHeight);
    ctx.fillStyle = "#0095DD";
    ctx.fill();
    ctx.closePath();
}

function draw() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawBricks();
    drawBall();
    drawPaddle();
    drawScore();
    drawLives();
    collisionDetection();
    
    
    if(x + dx > canvas.width-ballRadius || x + dx < ballRadius) { //좌우 끝에 공이 도달했다면 반대로 이동
        dx = -dx;
    }
    if(y + dy < ballRadius) { //위 끝에 공이 닿았다면 아래로 이동.
        dy = -dy;
    }else if(y + dy > canvas.height-ballRadius) { //공이 아래에서 닿았을때
    	if(x > paddleX && x < paddleX + paddleWidth) { //패들에 닿았다면 반대로 이동
        dy = -dy;
   		}
   		else{ //아니라면 게임오버 창을 띄움.
    		lives--;
			if(!lives) {
   				alert("GAME OVER");
    			document.location.reload();
    			clearInterval(interval); // Needed for Chrome to end game
			}
			else {
    			x = canvas.width/2;
    			y = canvas.height-30;
    			dx = 2;
    			dy = -2;
   				 paddleX = (canvas.width-paddleWidth)/2;
			}
    	}
	}
    
    if(rightPressed && paddleX < canvas.width-paddleWidth) {
        paddleX += 7;
    }
    else if(leftPressed && paddleX > 0) {
        paddleX -= 7;
    }
    x += dx;
    y += dy;
    requestAnimationFrame(draw);
}
draw();


</script>

</body>
</html>