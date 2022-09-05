import {Component, OnInit, ChangeDetectorRef, Input} from '@angular/core';
import {SpinnerService} from "../../core/services/spinner/spinner.service";


@Component({
  selector: 'app-spinner',
  templateUrl: './spinner.component.html',
  styleUrls: ['./spinner.component.scss']
})
export class SpinnerComponent implements OnInit {

  @Input() showSpinner ?: boolean;

  constructor(private spinnerService: SpinnerService, private cdRef: ChangeDetectorRef) {

  }

  ngOnInit() {
    this.init();
  }

  init() {

    this.spinnerService.getSpinnerObserver().subscribe((status) => {
      this.showSpinner = (status === 'start');
      this.cdRef.detectChanges();
    });
  }

}
