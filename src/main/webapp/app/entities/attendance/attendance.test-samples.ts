import dayjs from 'dayjs/esm';

import { IAttendance, NewAttendance } from './attendance.model';

export const sampleWithRequiredData: IAttendance = {
  id: 7996,
  dateOfwork: dayjs('2024-10-08'),
  checkInTime: dayjs('2024-10-08T02:10'),
  checkOutTime: dayjs('2024-10-07T23:10'),
  workHour: 30044.54,
};

export const sampleWithPartialData: IAttendance = {
  id: 5181,
  dateOfwork: dayjs('2024-10-07'),
  checkInTime: dayjs('2024-10-07T12:06'),
  checkOutTime: dayjs('2024-10-08T01:42'),
  workHour: 4725.42,
};

export const sampleWithFullData: IAttendance = {
  id: 16999,
  dateOfwork: dayjs('2024-10-07'),
  checkInTime: dayjs('2024-10-07T09:35'),
  checkOutTime: dayjs('2024-10-07T11:32'),
  workHour: 18071.46,
};

export const sampleWithNewData: NewAttendance = {
  dateOfwork: dayjs('2024-10-07'),
  checkInTime: dayjs('2024-10-07T07:47'),
  checkOutTime: dayjs('2024-10-07T19:59'),
  workHour: 31642.13,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
