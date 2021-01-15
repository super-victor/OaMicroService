<template>
  <transition name="fade">
   <view
			class="mask"
      v-show="isActive"
      :class="{ 'full-screen': isFullScreen }"
      :style="{ backgroundColor }"
    >
      <view class="spinner" :style="{ transform:`translate(-50%, -${translateY}%)`}">
        <slot>
					<shrinkRect :color="color" :size="size"/>
        </slot>
        <view
          v-if="text.length"
          :style="{ color:textColor }">
          {{ text }}
        </view>
      </view>
    </view>
  </transition>
</template>

<script>
import shrinkRect from './loaders/shrink-rect.vue'

export default {
  name: 'ourLoading',
  components: { 
			shrinkRect
	},
  props: {
    active: Boolean,
		translateY: {
			type: Number,
			default: 150
		},
    text: {
      type: String,
      default: ''
    },
    color: {
      type: String,
      default: '#5383EC'
    },
		textColor: {
			type: String,
			default: '#263D6E'
		},
    isFullScreen: {
      type: Boolean,
      default: true
    },
    backgroundColor: {
      type: String,
      default: 'rgba(255, 255, 255, .9)'
    },
    size: {
      type: Number,
      default: 40
    }
  },
  data () {
    return {
      isActive: this.active || false
    }
  },
  watch: {
    active (value) {
      this.isActive = value
    }
  }
}
</script>

<style scoped>
.mask {
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
	z-index: 3000;
	transition: opacity .3s linear;
}

.full-screen {
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
}

.spinner {
	position: absolute;
	top: 50%;
	left: 50%;
	text-align: center;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity .3s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>