import { Pipe, PipeTransform } from '@angular/core';
import * as dayjs from "dayjs";

@Pipe({
  name: 'dateFormat'
})
export class DateFormatPipe implements PipeTransform {

  transform(date: Date): string {
    return dayjs(date).format("DD MMM YYYY");
  }

}
