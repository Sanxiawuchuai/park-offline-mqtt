package com.drzk.utils;

import java.time.LocalTime;

public class PeriodTime {
		private LocalTime startTime;
		private LocalTime endTim;

		/**
		 * startTime.
		 *
		 * @return the startTime
		 * @since JDK 1.8
		 */
		public LocalTime getStartTime() {
			return startTime;
		}

		/**
		 * startTime.
		 *
		 * @param startTime the startTime to set
		 * @since JDK 1.8
		 */
		public void setStartTime(LocalTime startTime) {
			this.startTime = startTime;
		}

		/**
		 * endTim.
		 *
		 * @return the endTim
		 * @since JDK 1.8
		 */
		public LocalTime getEndTim() {
			return endTim;
		}

		/**
		 * endTim.
		 *
		 * @param endTim the endTim to set
		 * @since JDK 1.8
		 */
		public void setEndTim(LocalTime endTim) {
			this.endTim = endTim;
		}

	}