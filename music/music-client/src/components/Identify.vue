<template>
  <div class="s_canvas">
    <canvas id="s_canvas" :width="contentWidth" :height="contentHeight"></canvas>
  </div>
</template>
<script>
  export default {
    name: 'Identify',
    props: {
      identifyCode: {
        type: String,
        default: '1234'
      },
      fontSizeMin: {
        type: Number,
        default: 16
      },
      fontSizeMax: {
        type: Number,
        default: 40
      },
      backgroundColorMin: {
        type: Number,
        default: 180
      },
      backgroundColorMax: {
        type: Number,
        default: 240
      },
      colorMin: {
        type: Number,
        default: 50
      },
      colorMax: {
        type: Number,
        default: 160
      },
      lineColorMin: {
        type: Number,
        default: 40
      },
      lineColorMax: {
        type: Number,
        default: 180
      },
      dotColorMin: {
        type: Number,
        default: 0
      },
      dotColorMax: {
        type: Number,
        default: 255
      },
      contentWidth: {
        type: Number,
        default: 112
      },
      contentHeight: {
        type: Number,
        default: 38
      }
    },
    methods: {
      // 生成一个随机数
      randomNum(min, max) {
        return Math.floor(Math.random() * (max - min) + min)
      },
      // 生成一个随机的颜色
      randomColor(min, max) {
        let r = this.randomNum(min, max)
        let g = this.randomNum(min, max)
        let b = this.randomNum(min, max)
        return 'rgb(' + r + ',' + g + ',' + b + ')'
      },
      drawPic() {
        let canvas = document.getElementById('s_canvas')
        let text = canvas.getContext('2d')
        text.textBaseline = 'bottom'
        // 绘制背景
        text.fillStyle = this.randomColor(this.backgroundColorMin, this.backgroundColorMax)
        text.fillRect(0, 0, this.contentWidth, this.contentHeight)
        // 绘制文字
        for (let i = 0; i < this.identifyCode.length; i++) {
          this.drawText(text, this.identifyCode[i], i)
        }
        this.drawLine(text)
        this.drawDot(text)
      },
      drawText(text, txt, i) {
        text.fillStyle = this.randomColor(this.colorMin, this.colorMax)
        text.font = this.randomNum(this.fontSizeMin, this.fontSizeMax) + 'px SimHei'
        let x = (i + 1) * (this.contentWidth / (this.identifyCode.length + 1))
        let y = this.randomNum(this.fontSizeMax, this.contentHeight - 5)
        var deg = this.randomNum(-45, 45)
        // 修改坐标原点和旋转角度
        text.translate(x, y)
        text.rotate(deg * Math.PI / 180)
        text.fillText(txt, 0, 0)
        // 恢复坐标原点和旋转角度
        text.rotate(-deg * Math.PI / 180)
        text.translate(-x, -y)
      },
      drawLine(text) {
        // 绘制干扰线
        for (let i = 0; i < 8; i++) {
          text.strokeStyle = this.randomColor(this.lineColorMin, this.lineColorMax)
          text.beginPath()
          text.moveTo(this.randomNum(0, this.contentWidth), this.randomNum(0, this.contentHeight))
          text.lineTo(this.randomNum(0, this.contentWidth), this.randomNum(0, this.contentHeight))
          text.stroke()
        }
      },
      drawDot(text) {
        // 绘制干扰点
        for (let i = 0; i < 100; i++) {
          text.fillStyle = this.randomColor(0, 255)
          text.beginPath()
          text.arc(this.randomNum(0, this.contentWidth), this.randomNum(0, this.contentHeight), 1, 0, 2 * Math.PI)
          text.fill()
        }
      }
    },
    watch: {
      identifyCode() {
        this.drawPic()
      }
    },
    mounted() {
      this.drawPic()
    }
  }

</script>
<style scoped>
  @import '../assets/css/identify.css';

</style>
