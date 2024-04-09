import { Component } from '@angular/core';
import { Axios } from 'axios';
import { AxiosService } from '../axios.service';

@Component({
  selector: 'app-auth-content',
  templateUrl: './auth-content.component.html',
  styleUrls: ['./auth-content.component.css'],
})
export class AuthContentComponent {
  data: string[] = [];

  constructor(private axiosService: AxiosService) {}

  ngOnInit(): void {
    this.axiosService
      .request('GET', '/messages', {})
      .then((resp) => (this.data = resp.data));
  }
}
