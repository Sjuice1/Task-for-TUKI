import { Directive, ElementRef, HostBinding, HostListener } from "@angular/core";

@Directive({
    selector:'[appDropdownMenu]'
})
export class DropDownMenuDirective{

    constructor(private elementRef: ElementRef){}

    @HostBinding('class.open') isOpen : boolean = false;

    @HostListener('click',['$event']) toggleOpen(event : Event){
        if ((event.target as Node).contains(this.elementRef.nativeElement)) {
            this.isOpen = !this.isOpen;
        }
    }

    @HostListener('document:click',['$event'])
    onClickOutside(event : Event){
        if (!this.elementRef.nativeElement.contains(event.target as Node)) {
            this.isOpen = false;
        }
    }
}