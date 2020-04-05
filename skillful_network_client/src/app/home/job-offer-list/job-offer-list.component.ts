import { MOCK_CANDIDATURE } from './../../shared/models/mock.candidature';
import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { CandidatureService } from 'src/app/shared/services/candidature.service';
import { IPost } from 'src/app/shared/models/mock.candidature';
import { JobOfferService } from '../../shared/services/job-offer.service';
import { JobOffer } from '../../shared/models/job-offer';
import { Candidature } from '../../shared/models/candidature';
import { Observable } from 'rxjs';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-job-offer-list',
  templateUrl: './job-offer-list.component.html',
  styleUrls: ['./job-offer-list.component.scss']
})
export class JobOfferListComponent implements OnInit {

  jobOfferId: number;
  displayedColumns: string[] = ['name', 'company', 'type', 'dateBeg', 'dateUpload', 'statut', 'plus_info'];
  dataSource = null;


  @ViewChild(MatSort, { static: true }) sort: MatSort;

  //initial page size
  pageEvent: PageEvent;
  pageSize: number;
  pageSizeOptions: number[] = [10, 25, 50, 100];
  pageIndex: number;
  
  hidePageSize: boolean = false;
  showFirstLastButtons: boolean = false;
  
  //Pagination variables
  @ViewChild(MatPaginator) paginator: MatPaginator;

  //Event page to get size entities and page index
  // pageEvent($event) {
  //   this.pageSize = $event.pageSize;
  //   this.pageIndex = $event.pageIndex;
  // }

  constructor(public candidatureService: CandidatureService, public offerService: JobOfferService) {
  }

  ngOnInit(): void {
    this.getJobOffers(this.paginator);
  }

  getJobOffers(event?:PageEvent) {
    if (event == null) {
      this.pageSize =10;
      this.pageIndex =1;
    } else {
      this.pageSize= event.pageSize;
      this.pageIndex = event.pageIndex;
    }
      this.offerService.findAll(this.pageIndex,this.pageSize).then(res => {
      this.dataSource = new MatTableDataSource<JobOffer>(res);
      console.log("pageSize function: " + this.pageSize);
      this.dataSource.sort = this.sort;
    });
  }

  searchByNameOrCompany(form: NgForm) {
    this.offerService.getOffersBySearch(form.value.keyword, form.value.page, form.value.size).then(res => {
      this.dataSource = new MatTableDataSource<JobOffer>(res.content);
      this.dataSource.sort = this.sort;
    });
  }

  //this.dataSource.paginator = this.paginator;
  //this.dataSource.sort = this.sort;

  findApplicationJob(jobOfferId: number) {
    this.candidatureService.findApplicationJobByJobOffer(jobOfferId).then(res => {
      this.dataSource = new MatTableDataSource<Candidature>(res);
    });
  }
}


