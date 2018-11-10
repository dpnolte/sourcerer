/* generated @ 2018-11-10T13:55:15.315 */
/// <reference path='./main.types.d.ts' />

declare namespace CoordinatorlayoutTypes {
  export interface CoordinatorLayoutAttributes extends MainTypes.ViewGroupAttributes {
  }

  export interface CoordinatorLayoutLayoutParamsAttributes extends MainTypes.ViewGroupMarginLayoutParamsAttributes {
    android_layout_gravity?: number;
    layout_anchor?: string;
    layout_anchorGravity?: any;
    layout_behavior?: string;
    layout_dodgeInsetEdges?: string;
    layout_insetEdge?: LayoutInsetEdgeEnum;
    layout_keyline?: number;
  }

  export enum LayoutInsetEdgeEnum { 'none', 'top', 'bottom', 'left', 'right', 'start', 'end' }
}
